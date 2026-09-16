package com.jorji.modifier;

import java.util.List;

@FunctionalInterface 
public interface ModifierSource {
    List<Modifier> modifiers();
}
