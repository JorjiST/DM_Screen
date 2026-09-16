package com.jorji.content;

import com.jorji.modifier.ModifierSource;
import com.jorji.modifier.Modifier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class Item implements ModifierSource {
    private String id;
    private String name;
    private String description;
    private EquipmentSlot equipmentSlot;
    private List<Modifier> modifiers;

    public Item() {
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