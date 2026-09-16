package com.jorji.modifier.handler;

import com.jorji.armor.ArmorClass;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierMath;

import lombok.extern.log4j.Log4j;

@Log4j
public class ArmorClassModifierHandler extends AbstractModifierHandler {

    public ArmorClassModifierHandler() {
        super("armor");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        log.info("Applying armor class modifier: " + modifier + " to hero: " + hero.getName());
        ArmorClass armorClass = hero.getArmorClass();
        int amount = ModifierMath.requireIntValue(modifier);
        armorClass.setComputed(ModifierMath.applyIntOperation(modifier.operation(), armorClass.getComputed(), amount));    
    }
}
