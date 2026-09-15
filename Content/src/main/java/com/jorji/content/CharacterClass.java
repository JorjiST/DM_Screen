package com.jorji.content;

import com.jorji.modifier.Modifier;
import com.jorji.modifier.ModifierSource;

import java.util.List;


public record CharacterClass(List<Modifier> modifiers) implements ModifierSource {
}
