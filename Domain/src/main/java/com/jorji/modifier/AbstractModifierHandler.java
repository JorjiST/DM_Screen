package com.jorji.modifier;

import java.util.function.BiConsumer;
import java.util.function.ToIntFunction;

import com.jorji.hero.Hero;
import lombok.Getter;

@Getter
public abstract class AbstractModifierHandler implements ModifierHandler {

    private final String prefix;

    protected AbstractModifierHandler(String prefix){
        this.prefix = prefix;
    }

    protected void applyIntegerModifier(Hero hero, Modifier modifier, ToIntFunction<Hero> getter, BiConsumer<Hero, Integer> setter) {
        if(!(modifier.value() instanceof Integer amount)){
            throw new IllegalArgumentException(
                    "Expected integer value for modifier, got: " + modifier.value());
        }

        switch (modifier.operation()){
            case ADD -> setter.accept(hero, getter.applyAsInt(hero) + amount);
            case SET -> setter.accept(hero, amount);
            case GRANT -> throw new UnsupportedOperationException(
                    "GRANT is not applicable to this modifier");
        }
    }
}