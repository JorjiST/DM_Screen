package com.jorji.modifier;

import com.jorji.hero.Hero;

public interface ModifierHandler {
    void apply(Hero hero, Modifier modifier);
}