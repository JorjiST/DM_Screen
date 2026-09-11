package com.jorji.modifier;

import com.jorji.ModifierEngine;
import com.jorji.Character.Hero;

public class SpeedModifierHandler extends AbstractModifierHandler {

    private final ModifierEngine engine;

    public SpeedModifierHandler(ModifierEngine engine) {
        this.engine = engine;
    }

    @Override
    public void register() {
        engine.registerModifierHandler("speed", this);
    }

    @Override
    public void apply(Hero hero, Modifier modifier) {
        applyIntegerModifier(hero, modifier, Hero::getSpeed, Hero::setSpeed);
    }
    
}
