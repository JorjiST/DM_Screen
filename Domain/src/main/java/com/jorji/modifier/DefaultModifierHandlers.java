package com.jorji.modifier;

import java.util.List;

public final class DefaultModifierHandlers {
    public List<AbstractModifierHandler> all(){
        return List.of(
                new AbilityModifierHandler(),
                new SpeedModifierHandler(),
                new ArmorClassModifierHandler());
    }
}
