package com.jorji.modifier.handler;

import com.jorji.ability.Ability;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;

import lombok.extern.log4j.Log4j;

@Log4j
public class SavingThrowModifierHandler extends AbstractModifierHandler{

    public SavingThrowModifierHandler(){
        super("savingThrow");
    }

    @Override
    public void apply(Hero hero, String reminder, Modifier modifier) {
        log.info("Applying saving throw modifier: " + modifier + " to hero: " + hero.getName() + " for ability: " + reminder);
        Ability ability = Ability.valueOf(reminder.toUpperCase());

        switch (modifier.operation()) {
            case GRANT -> hero.getSavingThrowProficiencies().add(ability);
            case ADD, SET -> log.error("ADD/SET are not applicable to saving throw proficiency");
        }
    }
}
