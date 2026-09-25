package com.jorji.content;

import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;
import java.util.List;

public record Peculiarity(String id, String description, List<Modifier> modifiers) implements ModifierSource {

    @Override
    public List<Modifier> modifiers() {
        return modifiers;
    }
}
