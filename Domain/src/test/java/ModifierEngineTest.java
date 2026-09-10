import com.jorji.Ability.Ability;
import com.jorji.Ability.AbilityScore;
import com.jorji.Character.Hero;
import com.jorji.ModifierEngine;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.Operation;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ModifierEngineTest {

    @Test
    void applyModifier(){
        Hero hero = new Hero("elf", 1,1);
        hero.getAbilityScores().put(Ability.CHARISMA, new AbilityScore(1));
        ModifierEngine modifierEngine = new ModifierEngine();
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

        Hero hero = new Hero("elf", 1,1);
        hero.getAbilityScores().put(Ability.CHARISMA, score);

        ModifierEngine engine = new ModifierEngine();
        engine.apply(hero, List.of(new Modifier("ability.charisma", Operation.ADD, 10)));

        assertEquals(20, score.getComputed()); 
        assertEquals(15, score.effectiveValue()); 
}
}
