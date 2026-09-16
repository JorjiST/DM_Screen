package com.jorji.modifier.handler;

import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Getter
public abstract class AbstractModifierHandler implements ModifierHandler {

    private final String prefix;

    protected AbstractModifierHandler(String prefix){
        this.prefix = prefix;
    }

}