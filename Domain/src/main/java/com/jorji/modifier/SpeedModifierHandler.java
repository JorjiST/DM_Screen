package com.jorji.modifier;

import com.jorji.hero.Hero;
import lombok.Getter;

@Getter
public class SpeedModifierHandler extends AbstractModifierHandler {

    public SpeedModifierHandler() {
        super("speed");
    }

    @Override
    public void apply(Hero hero, Modifier modifier) {
        applyIntegerModifier(hero, modifier, Hero::getSpeed, Hero::setSpeed);
    }
    
}
