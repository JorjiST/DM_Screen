package com.jorji.Character;

import com.jorji.Ability.Abilities;
import com.jorji.Ability.AbilityScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Hero {
    private String raceId;
    private Integer speed;
    private int level;
    private final Map<Abilities, AbilityScore> abilityScores = new EnumMap<>(Abilities.class);

    {
        abilityScores.put(Abilities.CHARISMA, new AbilityScore(0));
        abilityScores.put(Abilities.CONSTITUTION, new AbilityScore(0));
        abilityScores.put(Abilities.DEXTERITY, new AbilityScore(0));
        abilityScores.put(Abilities.STRENGTH, new AbilityScore(0));
        abilityScores.put(Abilities.WISDOM, new AbilityScore(0));
        abilityScores.put(Abilities.INTELLIGENCE, new AbilityScore(0));
    }
}
