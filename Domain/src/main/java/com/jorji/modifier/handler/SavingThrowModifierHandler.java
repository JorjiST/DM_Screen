package com.jorji.modifier.handler;

import com.jorji.ability.Ability;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;

public class SavingThrowModifierHandler extends AbstractModifierHandler{

    public SavingThrowModifierHandler(){
        super("savingThrow");
    }

    @Override
    public void apply(Hero hero, String reminder, Modifier modifier) {
        Ability ability = Ability.valueOf(reminder.toUpperCase());

        switch (modifier.operation()) {
            case GRANT -> hero.getSavingThrowProficiencies().add(ability);
            case ADD, SET -> throw new UnsupportedOperationException(
                    "ADD/SET are not applicable to saving throw proficiency");
        }
    }
}
