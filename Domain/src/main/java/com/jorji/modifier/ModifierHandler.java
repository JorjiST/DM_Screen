package com.jorji.modifier;

import com.jorji.Character.Hero;

public interface ModifierHandler {
    void register();
    void apply(Hero hero, Modifier modifier);
}