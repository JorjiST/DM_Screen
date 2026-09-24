package com.jorji.modifier.handler;

import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierMath;
import com.jorji.stat.HitPoint;

public class HitPointModifierHandler extends AbstractModifierHandler {

    public HitPointModifierHandler(){
        super("hitPoint");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        int amount = ModifierMath.requireIntValue(modifier);
        HitPoint hitPoint = hero.getHitPoint();
        if(remainder.equals("temp")){
            hitPoint.setTempHitPoints(ModifierMath.applyIntOperation(modifier.operation(), hitPoint.getTempHitPoints(), amount));
        }
        else {
            hitPoint.setComputed(ModifierMath.applyIntOperation(modifier.operation(), hitPoint.getComputed(), amount));
        }
    }
}
