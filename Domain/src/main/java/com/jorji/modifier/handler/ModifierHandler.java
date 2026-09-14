package com.jorji.modifier.handler;

import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;

public interface ModifierHandler {
    void apply(Hero hero, String remainder, Modifier modifier);
}