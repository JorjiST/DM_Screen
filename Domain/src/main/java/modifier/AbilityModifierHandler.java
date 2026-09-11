package modifier;

import com.jorji.ModifierEngine;
import com.jorji.Character.Hero;
import com.jorji.modifier.Modifier;

public class AbilityModifierHandler extends AbstractModifierHandler {

    private final ModifierEngine engine;

    public AbilityModifierHandler(ModifierEngine engine) {
        this.engine = engine;
    }

    @Override
    public void register() {
        engine.registerModifierHandler("ability", this);
    }

    @Override
    public void apply(Hero hero, Modifier modifier) {
        String[] parts = modifier.target().split("\\.", 2);
        String abilityName = parts[1];
        applyIntegerModifier(
                hero,
                modifier,
                h -> h.getAbilityScores().get(abilityName).effectiveValue(),
                (h, value) -> h.getAbilityScores().get(abilityName).setComputed(value)
        );
    }
    
}
