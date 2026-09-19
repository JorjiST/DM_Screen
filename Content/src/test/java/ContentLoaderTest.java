import com.jorji.content.CharacterClass;
import com.jorji.content.Race;
import com.jorji.content.Spell;
import com.jorji.content.enums.SpellComponent;
import com.jorji.loader.ContentLoader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentLoaderTest {
    @Test
    void loadsRaceFromJson() throws Exception {
        ContentLoader loader = new ContentLoader();
        Path path = Path.of(getClass().getResource("/races/elf.json").toURI());

        Race elf = loader.loadRace(path);

        assertEquals("elf", elf.id());
        assertEquals("Эльф", elf.name());
        assertEquals(30, elf.baseSpeed());
        assertEquals(1, elf.modifiers().size());
        assertEquals("ability.dexterity", elf.modifiers().getFirst().target());
    }

    @Test
    void loadsClassFromJson() throws Exception {
        ContentLoader loader = new ContentLoader();
        Path path = Path.of(getClass().getResource("/classes/fighter.json").toURI());

        CharacterClass fighter = loader.loadClass(path);

        assertEquals("fighter", fighter.id());
        assertEquals("воин", fighter.name());
        assertEquals("ability.strength", fighter.modifiers().getFirst().target());
    }

    @Test
    void loadsSpellFromJson() throws Exception {
        ContentLoader loader = new ContentLoader();
        Path path = Path.of(getClass().getResource("/spells/divineFavor.json").toURI());

        Spell spell = loader.loadSpell(path);
        assertEquals("divineFavor", spell.id());
        assertEquals("Аура живучести", spell.name());
        assertEquals(1, spell.level());
        assertEquals(Set.of(SpellComponent.VERBAL, SpellComponent.SOMATIC), spell.components());
    }
}
