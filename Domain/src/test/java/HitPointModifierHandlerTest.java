import com.jorji.content.Item;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.content.enums.Operation;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierEngine;
import com.jorji.modifier.handler.HitPointModifierHandler;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitPointModifierHandlerTest {

    @Test
    void appliesModifier(){
        Hero hero = new Hero("Alan", "elf", "fighter", new Speed(10), 1, new HitPoint(0), false);

        Modifier hitPointMod = new Modifier("hitPoint", Operation.ADD, 5);
        Modifier tempHitPointMod = new Modifier("hitPoint.temp", Operation.ADD, 10);

        Item item = new Item("n", "n", "n", EquipmentSlot.HAND, List.of(hitPointMod, tempHitPointMod));

        ModifierEngine modifierEngine = new ModifierEngine();
        modifierEngine.registerModifierHandler(new HitPointModifierHandler());

        int startedComputedHitPoints = hero.getHitPoint().getComputed();
        int startedTempHitPoints = hero.getHitPoint().getTempHitPoints();
        int startedGeneralHitPoints = hero.getHitPoint().getGeneralHealth();

        modifierEngine.apply(hero, item.getModifiers());

        int currentComputedHitPoints = hero.getHitPoint().getComputed();
        int currentTempHitPoints = hero.getHitPoint().getTempHitPoints();
        int currentGeneralHitPoints = hero.getHitPoint().getGeneralHealth();

        assertEquals(startedComputedHitPoints + (int) hitPointMod.value(), currentComputedHitPoints);
        assertEquals(startedTempHitPoints + (int) tempHitPointMod.value(), currentTempHitPoints);
        assertEquals(startedGeneralHitPoints + (int) hitPointMod.value() + (int) tempHitPointMod.value(), currentGeneralHitPoints);
    }
}
