package com.jorji.content;

import com.jorji.content.enums.ArmorCategory;
import com.jorji.content.enums.EquipmentSlot;
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
                 String description,
                 EquipmentSlot equipmentSlot,
                 List<Modifier> modifiers,
                 ArmorCategory armorCategory,
                 Integer baseArmorClass,
                 Integer minStrength) {
        this.armorCategory = armorCategory;
        this.baseArmorClass = baseArmorClass;
        this.minStrength = minStrength;
        super(id, name, description, equipmentSlot, modifiers);
    }
}
