package com.jorji.content;

import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;

import java.util.List;

public record CharacterClass(String id, String name, List<Modifier> modifiers) implements ModifierSource {
}
