import com.jorji.ContentRegistry;
import com.jorji.HeroRepository;
import com.jorji.armor.ArmorClass;
import com.jorji.content.enums.Operation;
import com.jorji.hero.Hero;
import com.jorji.modifier.*;
import com.jorji.modifier.handler.AbilityModifierHandler;
import com.jorji.modifier.handler.SpeedModifierHandler;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeroRepositoryTest {

    private HeroRecalculationService recalculationService;
    private HeroRepository heroRepository = new HeroRepository();
    private Hero hero;


    @BeforeEach
    void setUp() {
        ModifierEngine engine = new ModifierEngine();
        engine.registerModifierHandler(new AbilityModifierHandler());
        engine.registerModifierHandler(new SpeedModifierHandler());
        recalculationService = new HeroRecalculationService(engine);
    }

    @Test
    void writesHero() {
        hero = new Hero("Alan", "elf", "fighter", new Speed(10), 1, new HitPoint(0), false, new ArmorClass(0));
        EquipmentService equipmentService = new EquipmentService(recalculationService, new ContentRegistry());
        ModifierSource raceSource = new TestSource(List.of(
                new Modifier("ability.dexterity", Operation.ADD, 200)
        ));
        recalculationService.recalculate(hero, List.of(raceSource));
        equipmentService.equip(hero, "ring");

        Path path = Path.of("hero.json");
        heroRepository.save(hero, path);
        assertTrue(Files.exists(path));
    }

    @Test
    void readsHero() {
        writesHero();
        Hero readHero = heroRepository.read(Path.of("hero.json"), new ContentRegistry());
        assertEquals(hero, readHero);
    }

    private record TestSource(List<Modifier> modifiers) implements ModifierSource {}
}
