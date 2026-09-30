import com.jorji.ContentRegistry;
import com.jorji.HeroRepository;
import com.jorji.content.Item;
import com.jorji.content.enums.Operation;
import com.jorji.hero.Hero;
import com.jorji.modifier.*;
import com.jorji.modifier.handler.AbilityModifierHandler;
import com.jorji.modifier.handler.SpeedModifierHandler;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class HeroRepositoryTest {

    private HeroRecalculationService recalculationService;

    @BeforeEach
    void setUp() {
        ModifierEngine engine = new ModifierEngine();
        engine.registerModifierHandler(new AbilityModifierHandler());
        engine.registerModifierHandler(new SpeedModifierHandler());
        recalculationService = new HeroRecalculationService(engine);
    }

    @Test
    void writesHero() throws IOException {
        EquipmentService equipmentService = new EquipmentService(recalculationService, new ContentRegistry());
        Hero hero = new Hero("Alan", "elf", "fighter", new Speed(10), 1, new HitPoint(0), false);
        ModifierSource raceSource = new TestSource(List.of(
                new Modifier("ability.dexterity", Operation.ADD, 200)
        ));
        recalculationService.recalculate(hero, List.of(raceSource));
        equipmentService.equip(hero, "ring");
        HeroRepository heroRepository = new HeroRepository();
        heroRepository.save(hero, Path.of("hero.json"));
    }

    private record TestSource(List<Modifier> modifiers) implements ModifierSource {}
}
