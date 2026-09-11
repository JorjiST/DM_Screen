package com.jorji;

import com.jorji.Ability.Ability;
import com.jorji.Ability.AbilityScore;
import com.jorji.Character.Hero;
import com.jorji.modifier.Modifier;

import com.jorji.modifier.ModifierHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModifierEngine {

    private final Map<String, ModifierHandler> modifierHandlers = new HashMap<>();


    public void registerModifierHandler(String targetPrefix, ModifierHandler handler) {
        modifierHandlers.put(targetPrefix, handler);
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

        // //Подумать над рефакторингом в паттерн Стратегия
        // switch (prefix) {
        //     case "ability" -> applyAbilityModifier(hero, parts[1], modifier);
        //     case "speed" -> applySpeedModifier(hero, modifier);
        //     default -> throw new UnsupportedOperationException(
        //             "Unsupported target: " + modifier.target());
        // }
    }

    private void applySpeedModifier(Hero hero, Modifier modifier){
        if(!(modifier.value() instanceof Integer amount)){
            throw new IllegalArgumentException(
                    "Expected integer value for ability modifier, got: " + modifier.value());
        }

        switch (modifier.operation()){
            case ADD -> hero.setSpeed(hero.getSpeed() + amount);
            case SET -> hero.setSpeed(amount);
            case GRANT -> throw new UnsupportedOperationException(
                    "GRANT is not applicable to ability scores");
        }
    }

    private void applyAbilityModifier(Hero hero, String abilityName, Modifier modifier) {
        Ability ability = Ability.valueOf(abilityName.toUpperCase());
        AbilityScore score = hero.getAbilityScores().get(ability);

        if (!(modifier.value() instanceof Integer amount)) {
            throw new IllegalArgumentException(
                    "Expected integer value for ability modifier, got: " + modifier.value());
        }

        switch (modifier.operation()) {
            case ADD -> score.setComputed(score.getComputed() + amount);
            case SET -> score.setComputed(amount);
            case GRANT -> throw new UnsupportedOperationException(
                    "GRANT is not applicable to ability scores");
        }
    }
}
