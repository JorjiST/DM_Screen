package com.jorji.hero;

import com.jorji.Ability.Ability;
import com.jorji.Ability.AbilityScore;
import com.jorji.Ability.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class Hero {
    private String raceId;
    private Integer speed;
    private int level;
    private int armorClass;
    private final Map<Ability, AbilityScore> abilityScores = new EnumMap<>(Ability.class);
    private final Set<Skill> skillProficiencies = EnumSet.noneOf(Skill.class);

    {
        abilityScores.put(Ability.CHARISMA, new AbilityScore(0));
        abilityScores.put(Ability.CONSTITUTION, new AbilityScore(0));
        abilityScores.put(Ability.DEXTERITY, new AbilityScore(0));
        abilityScores.put(Ability.STRENGTH, new AbilityScore(0));
        abilityScores.put(Ability.WISDOM, new AbilityScore(0));
        abilityScores.put(Ability.INTELLIGENCE, new AbilityScore(0));
    }
}
