package com.jorji;

import com.jorji.hero.Hero;
import com.jorji.modifier.AbstractModifierHandler;
import com.jorji.modifier.Modifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifierEngine {

    private final Map<String, AbstractModifierHandler> modifierHandlers = new HashMap<>();


    public void registerModifierHandler(AbstractModifierHandler handler) {
        modifierHandlers.put(handler.getPrefix(), handler);
    }

    public void apply(Hero hero, List<Modifier> modifierList){
        for(Modifier modifier : modifierList){
            applyOne(hero, modifier);
        }
    }

    private void applyOne(Hero hero, Modifier modifier) {
        String[] parts = modifier.target().split("\\.", 2);
        String prefix = parts[0];
        modifierHandlers.get(prefix).apply(hero, modifier);
    }
}
