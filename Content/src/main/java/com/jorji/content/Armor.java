package com.jorji.content;

import com.jorji.modifier.Modifier;
import lombok.Getter;

import java.util.List;

@Getter
public class Armor extends Item {
    private final ArmorCategory armorCategory;
    private final Integer baseArmorClass;
    private final Integer minStrength;

    public Armor(String id,
                 String name,
                 EquipmentSlot equipmentSlot,
                 List<Modifier> modifiers,
                 ArmorCategory armorCategory,
                 Integer baseArmorClass,
                 Integer minStrength) {
        this.armorCategory = armorCategory;
        this.baseArmorClass = baseArmorClass;
        this.minStrength = minStrength;
        super(id, name, equipmentSlot, modifiers);
    }
}
