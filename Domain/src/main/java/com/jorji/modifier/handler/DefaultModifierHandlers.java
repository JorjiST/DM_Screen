package com.jorji.modifier.handler;

import java.util.List;

public final class DefaultModifierHandlers {
    public List<AbstractModifierHandler> all(){
        return List.of(
                new AbilityModifierHandler(),
                new SpeedModifierHandler(),
                new ArmorClassModifierHandler(),
                new SavingThrowModifierHandler());
    }
}
