package com.jorji.armor;

import com.jorji.content.enums.ArmorCategory;
import com.jorji.ability.Ability;
import com.jorji.content.Armor;
import com.jorji.hero.Hero;

import java.util.Optional;

public class ArmorClassCalculator {
    public int calculate(Hero hero, Optional<Armor> equippedArmor, boolean hasShield) {
        int dexModifier = hero.getAbilityModifier(Ability.DEXTERITY);

        int base = equippedArmor
                .map(armor -> switch (armor.getArmorCategory()) {
                    case ArmorCategory.LIGHT -> armor.getBaseArmorClass() + dexModifier;
                    case ArmorCategory.MEDIUM -> armor.getBaseArmorClass() + Math.min(dexModifier, 2);
                    case ArmorCategory.HEAVY -> armor.getBaseArmorClass();
                })
                .orElse(10 + dexModifier);

        return hasShield ? base + 2 : base;
    }
}
