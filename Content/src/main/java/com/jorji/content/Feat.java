package com.jorji.content;

import com.jorji.HasID;
import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;

import java.util.List;

public record Feat(
    String id, 
    String describe, 
    List<Modifier> modifiers
) implements ModifierSource, HasID {

    @Override
    public String getId() {
        return id;
    }
}
