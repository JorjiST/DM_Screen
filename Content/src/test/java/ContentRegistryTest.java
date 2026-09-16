import com.jorji.ContentRegistry;
import com.jorji.Operation;
import com.jorji.content.CharacterClass;
import com.jorji.content.EquipmentSlot;
import com.jorji.content.Item;
import com.jorji.content.Race;
import com.jorji.loader.ContentLoader;
import com.jorji.modifier.Modifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentRegistryTest {

    private ContentLoader contentLoader;
    private ContentRegistry contentRegistry;

    @BeforeEach
    void setUp() {
        contentLoader = new ContentLoader();
        contentRegistry = new ContentRegistry();
    }

    @Test
    void registerAllContentFromContentLoader() throws IOException, URISyntaxException {
        Path racesMarker = Path.of(getClass().getResource("/items").toURI());
        Path rootDirectory = racesMarker.getParent();
        contentRegistry.loadAll(contentLoader, rootDirectory);

        CharacterClass testCharacterClass = new CharacterClass(
                "fighter",
                "воин",
                List.of(new Modifier("ability.strength", Operation.ADD, 100)));

        Race testCharacterRace = new Race(
                "elf",
                "Эльф",
                30,
                List.of(new Modifier("ability.dexterity", Operation.ADD, 2)));

        Item testItem = new Item(
                "ring",
                "кольцо",
                "Нося это кольцо, вы получаете +2 к харизме",
                EquipmentSlot.HAND,
                List.of(new Modifier("ability.charisma", Operation.ADD, 2)));

        assertEquals(testCharacterClass, contentRegistry.getCharacterClass("fighter"));
        assertEquals(testCharacterRace, contentRegistry.getRace("elf"));
        assertEquals(testItem, contentRegistry.getItem("ring"));

    }
}
