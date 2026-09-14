package com.jorji.modifier.handler;

import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierMath;
import com.jorji.stat.Speed;

public class SpeedModifierHandler extends AbstractModifierHandler {

    public SpeedModifierHandler() {
        super("speed");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        Speed speed = hero.getSpeed();
        int amount = ModifierMath.requireIntValue(modifier);
        speed.setComputed(ModifierMath.applyIntOperation(modifier.operation(), speed.getComputed(), amount));
    }
}
