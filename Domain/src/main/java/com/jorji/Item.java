package com.jorji;

import com.jorji.armor.ArmorCategory;
import com.jorji.modifier.Modifier;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Item implements ModifierSource {
    private String id;
    private String name;
    private EquipmentSlot equipmentSlot;
    private ArmorCategory armorCategory;   // null для не-брони
    private Integer baseArmorClass;         // null для не-брони
    private List<Modifier> modifiers;
}