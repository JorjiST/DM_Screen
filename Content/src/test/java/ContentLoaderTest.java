import com.jorji.Race;
import com.jorji.loader.ContentLoader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentLoaderTest {
    @Test
    void loadsRaceFromJson() throws Exception {
        ContentLoader loader = new ContentLoader();
        Path path = Path.of(getClass().getResource("/elf.json").toURI());

        Race elf = loader.loadRace(path);

        assertEquals("elf", elf.id());
        assertEquals("Эльф", elf.name());
        assertEquals(30, elf.baseSpeed());
        assertEquals(2, elf.modifiers().size());
        assertEquals("ability.dexterity", elf.modifiers().get(0).target());
    }
}
