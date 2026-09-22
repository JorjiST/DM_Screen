package com.jorji.content;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.modifier.ModifierSource;
import com.jorji.modifier.Modifier;
import lombok.Getter;
import java.util.List;

@Getter
public class Item implements ModifierSource {
    private final String id;
    private final String name;
    private final String description;
    private final EquipmentSlot equipmentSlot;
    private final List<Modifier> modifiers;

    @JsonCreator
    public Item(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("equipmentSlot") EquipmentSlot equipmentSlot,
            @JsonProperty("modifiers") List<Modifier> modifiers
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.equipmentSlot = equipmentSlot;
        this.modifiers = modifiers;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Item item)) return false;
        if (o == this) return true;
        return item.id.equals(this.id) &&
                item.name.equals(this.name) &&
                item.description.equals(this.description) &&
                item.equipmentSlot.equals(this.equipmentSlot) &&
                item.modifiers.equals(this.modifiers);
    }

    @Override
    public List<Modifier> modifiers() {
        return modifiers;
    }
}