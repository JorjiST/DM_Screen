import com.jorji.ability.Ability;
import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;
import com.jorji.ModifierEngine;
import com.jorji.modifier.handler.AbilityModifierHandler;
import com.jorji.modifier.Modifier;
import com.jorji.Operation;

import com.jorji.modifier.handler.SpeedModifierHandler;

import com.jorji.stat.Speed;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class ModifierEngineTest {

    @Test
    void shouldRegisterItselfInEngine() {
        ModifierEngine mockEngine = mock(ModifierEngine.class);
        SpeedModifierHandler speedHandler = new SpeedModifierHandler();
        mockEngine.registerModifierHandler(speedHandler);
        verify(mockEngine).registerModifierHandler(speedHandler);
    }

    @Test
    void applyModifier(){
        Hero hero = new Hero("elf","classId", new Speed(1),1, false);
        hero.getAbilityScores().put(Ability.CHARISMA, new AbilityScore(1));

        ModifierEngine modifierEngine = new ModifierEngine();
        AbilityModifierHandler modifierHandler = new AbilityModifierHandler();

        modifierEngine.registerModifierHandler(modifierHandler);
        modifierEngine.apply(hero, List.of(new Modifier("ability.charisma", Operation.ADD, 10)));

        assertEquals(11, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }

    @Test
    void overrideTakesPriorityOverComputed() {
        AbilityScore score = new AbilityScore(10);
        score.setOverride(15);
        assertEquals(15, score.effectiveValue());
    }

    @Test
    void modifierEngineNeverTouchesOverride() {
        AbilityScore score = new AbilityScore(10);
        score.setOverride(15);

        Hero hero = new Hero("elf", "classId", new Speed(10),1, false);
        hero.getAbilityScores().put(Ability.CHARISMA, score);

        ModifierEngine engine = new ModifierEngine();
        AbilityModifierHandler modifierHandler = new AbilityModifierHandler();

        engine.registerModifierHandler(modifierHandler);
        engine.apply(hero, List.of(new Modifier("ability.charisma", Operation.ADD, 10)));

        assertEquals(20, score.getComputed()); 
        assertEquals(15, score.effectiveValue()); 
    }
}
