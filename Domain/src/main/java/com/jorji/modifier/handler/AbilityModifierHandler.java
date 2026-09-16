package com.jorji.modifier.handler;

import com.jorji.ability.Ability;
import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierMath;

import lombok.extern.log4j.Log4j;

@Log4j
public class AbilityModifierHandler extends AbstractModifierHandler {

    public AbilityModifierHandler() {
        super("ability");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        log.info("Applying ability modifier: " + modifier + " to hero: " + hero.getName() + " for ability: " + remainder);
        AbilityScore score = hero.getAbilityScores().get(Ability.valueOf(remainder.toUpperCase()));
        int amount = ModifierMath.requireIntValue(modifier);
        score.setComputed(ModifierMath.applyIntOperation(modifier.operation(), score.getComputed(), amount));
    }
}
