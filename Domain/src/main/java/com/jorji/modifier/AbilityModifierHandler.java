package com.jorji.modifier;

import com.jorji.Ability.Ability;
import com.jorji.ModifierEngine;
import com.jorji.Character.Hero;

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
        String abilityName = parts[1].toUpperCase();
        applyIntegerModifier(
                hero,
                modifier,
                h -> h.getAbilityScores().get(Ability.valueOf(abilityName)).getComputed(),
                (h, value) -> h.getAbilityScores().get(Ability.valueOf(abilityName)).setComputed(value)
        );
    }
    
}
