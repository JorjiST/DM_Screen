import com.jorji.ContentRegistry;
import com.jorji.hero.Hero;
import com.jorji.loader.ContentLoader;
import com.jorji.modifier.ModifierEngine;
import com.jorji.modifier.SpellService;
import com.jorji.modifier.handler.DefaultModifierHandlers;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SpellServiceTest {

    private ContentRegistry contentRegistry;
    private Hero hero;

    @BeforeEach
    void setUp() throws IOException, URISyntaxException {
        ModifierEngine modifierEngine = new ModifierEngine();
        DefaultModifierHandlers modifierHandlers = new DefaultModifierHandlers();
        modifierEngine.registerModifierHandler(modifierHandlers.all());

        contentRegistry = new ContentRegistry();


        Path path = Path.of(getClass().getResource("/spells").toURI());
        Path rootPath = path.getParent();
        contentRegistry.loadAll(rootPath);

        hero = new Hero("Alan", "elf", "fighter", new Speed(10), 1, new HitPoint(0), false);
    }

    @Test
    void grantsSpell(){
        SpellService spellService = new SpellService(contentRegistry);
        spellService.grant(hero, "divineFavor");

        assertEquals(Set.of(contentRegistry.getSpell("divineFavor")), hero.getSpells());
    }

    @Test
    void revokesSpell(){
        SpellService spellService = new SpellService(contentRegistry);
        spellService.revoke(hero, "divineFavor");

        assertFalse(hero.getSpells().contains(contentRegistry.getSpell("divineFavor")));
    }
}
