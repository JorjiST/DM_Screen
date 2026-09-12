package com.jorji.modifier;

import com.jorji.Ability.Ability;
import com.jorji.hero.Hero;

public class AbilityModifierHandler extends AbstractModifierHandler {

    public AbilityModifierHandler() {
        super("ability");
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
