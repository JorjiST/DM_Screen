package com.jorji.modifier.handler;

import com.jorji.ability.Skill;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;

import lombok.extern.log4j.Log4j;

@Log4j
public class SkillModifierHandler extends AbstractModifierHandler {

    protected SkillModifierHandler() {
        super("skill");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        log.info("Applying skill modifier: " + modifier + " to hero: " + hero.getName() + " for skill: " + remainder);
        Skill skill = Skill.valueOf(remainder.toUpperCase());
        switch (modifier.operation()) {
            case GRANT -> hero.getSkillProficiencies().add(skill);
            case ADD, SET -> {
                log.error("ADD/SET are not applicable to skill proficiency");
            }
        }
    }
}
