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
    private EquipmentSlot equipmentSlot;
    private List<Modifier> modifiers;

    @Override
    public List<Modifier> modifiers() {
        return List.of();
    }
}