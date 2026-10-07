package com.jorji.content;

import com.jorji.HasID;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;
import java.util.List;

public record Peculiarity(
    String id, 
    String description, 
    List<Modifier> modifiers
) implements ModifierSource, HasID {

    @Override
    public List<Modifier> modifiers() {
        return modifiers;
    }

    @Override
    public String getId() {
        return id;
    }
}
