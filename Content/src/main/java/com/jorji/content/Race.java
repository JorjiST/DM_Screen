package com.jorji.content;

import com.jorji.modifier.ModifierSource;
import com.jorji.modifier.Modifier;

import java.util.List;

public record Race(String id, String name, int baseSpeed, List<Modifier> modifiers) implements ModifierSource {

    @Override
    public List<Modifier> getModifiers() {
        return modifiers;
    }
}
