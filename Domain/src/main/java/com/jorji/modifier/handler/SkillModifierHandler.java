package com.jorji.modifier.handler;

import com.jorji.ability.Skill;
import com.jorji.hero.Hero;
import com.jorji.modifier.Modifier;

public class SkillModifierHandler extends AbstractModifierHandler {

    protected SkillModifierHandler() {
        super("skill");
    }

    @Override
    public void apply(Hero hero, String remainder, Modifier modifier) {
        Skill skill = Skill.valueOf(remainder.toUpperCase());
        switch (modifier.operation()) {
            case GRANT -> hero.getSkillProficiencies().add(skill);
            case ADD, SET -> throw new UnsupportedOperationException(
                    "ADD/SET are not applicable to saving throw proficiency");
        }
    }
}
