package com.jorji.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public Armor(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("modifiers") List<Modifier> modifiers,
            @JsonProperty("armorCategory") ArmorCategory armorCategory,
            @JsonProperty("baseArmorClass") Integer baseArmorClass,
            @JsonProperty("minStrength") Integer minStrength
    ) {
        super(id, name, description, EquipmentSlot.ARMOR, modifiers);
        this.armorCategory = armorCategory;
        this.baseArmorClass = baseArmorClass;
        this.minStrength = minStrength;
    }
}
