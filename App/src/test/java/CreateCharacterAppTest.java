import com.jorji.Ability.Abilities;
import com.jorji.Character.Hero;
import com.jorji.ModifierEngine;
import com.jorji.Race;
import com.jorji.loader.ContentLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCharacterAppTest {
    @Test
    void readAndCreateAndAddModifierTest() throws IOException, URISyntaxException {
        ContentLoader contentLoader = new ContentLoader();
        ModifierEngine modifierEngine = new ModifierEngine();
        Path path = Path.of(getClass().getResource("/elf.json").toURI());
        Race race = contentLoader.loadRace(path);
        Hero hero = new Hero(race.id(), 1);
        modifierEngine.apply(hero, race.modifiers());

        assertEquals(hero.getRaceId(), race.id());
        assertEquals(2, hero.getAbilityScores().get(Abilities.DEXTERITY).effectiveValue());
        assertEquals(10, hero.getAbilityScores().get(Abilities.STRENGTH).effectiveValue());

    }
}
