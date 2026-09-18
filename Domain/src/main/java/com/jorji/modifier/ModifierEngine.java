package com.jorji.modifier;

import com.jorji.hero.Hero;
import com.jorji.modifier.handler.AbstractModifierHandler;

import lombok.extern.log4j.Log4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class ModifierEngine {

    private final Map<String, AbstractModifierHandler> modifierHandlers = new HashMap<>();

    public void registerModifierHandler(AbstractModifierHandler handler) {
        log.info("Registering modifier handler for prefix: " + handler.getPrefix());
        modifierHandlers.put(handler.getPrefix(), handler);
    }

    public void registerModifierHandler(List<AbstractModifierHandler> handlers){
        for(AbstractModifierHandler handler : handlers){
            registerModifierHandler(handler);
        }
    }

    public void apply(Hero hero, List<Modifier> modifierList){
        for(Modifier modifier : modifierList){
            log.info("Applying modifier: " + modifier);
            applyOne(hero, modifier);
        }
    }

    private void applyOne(Hero hero, Modifier modifier) {
        String[] parts = modifier.target().split("\\.", 2);
        String prefix = parts[0];
        String reminder;
        if(parts.length == 2) reminder = parts[1];
        else reminder = parts[0];
        modifierHandlers.get(prefix).apply(hero, reminder, modifier);
    }
}
