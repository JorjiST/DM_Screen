import com.jorji.modifier.ModifierEngine;
import com.jorji.content.enums.Operation;
import com.jorji.ability.Ability;
import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;
import com.jorji.modifier.HeroRecalculationService;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;
import com.jorji.modifier.handler.AbilityModifierHandler;
import com.jorji.modifier.handler.SpeedModifierHandler;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeroRecalculationServiceTest {

    private HeroRecalculationService service;

    @BeforeEach
    void setUp() {
        ModifierEngine engine = new ModifierEngine();
        engine.registerModifierHandler(new AbilityModifierHandler());
        engine.registerModifierHandler(new SpeedModifierHandler());
        service = new HeroRecalculationService(engine);
    }

    @Test
    @DisplayName("appliesModifiersFromActiveSources")
    void appliesModifiersFromActiveSources() {
        Hero hero = new Hero("Alan", "elf", "fighter", new Speed(30), 1, new HitPoint(0),false);

        ModifierSource raceSource = new TestSource(List.of(
                new Modifier("ability.dexterity", Operation.ADD, 2)
        ));

        service.recalculate(hero, List.of(raceSource));

        assertEquals(12, hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue());
    }

    @Test
    @DisplayName("recalculateIsReversibleWhenSourceIsRemoved")
    void recalculateIsReversibleWhenSourceIsRemoved() {
        Hero hero = new Hero("Alan", "elf", "fighter", new Speed(30), 1, new HitPoint(0), false);

        ModifierSource ring = new TestSource(List.of(
                new Modifier("ability.charisma", Operation.ADD, 1)
        ));

        service.recalculate(hero, List.of(ring));
        assertEquals(11, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());

        // кольцо "снято" — источника больше нет в списке
        service.recalculate(hero, List.of());
        assertEquals(10, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }

    @Test
    @DisplayName("neverTouchesOverride")
    void neverTouchesOverride() {
        Hero hero = new Hero("Alan", "elf", "fighter", new Speed(30), 1,  new HitPoint(0), false);
        AbilityScore charisma = hero.getAbilityScores().get(Ability.CHARISMA);
        charisma.setOverride(99);

        ModifierSource source = new TestSource(List.of(
                new Modifier("ability.charisma", Operation.ADD, 5)
        ));

        service.recalculate(hero, List.of(source));

        assertEquals(15, charisma.getComputed());
        assertEquals(99, charisma.effectiveValue());
    }

    private record TestSource(List<Modifier> modifiers) implements ModifierSource {}
}