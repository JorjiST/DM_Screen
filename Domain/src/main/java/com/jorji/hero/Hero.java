package com.jorji.hero;

import com.jorji.EquipmentSlot;
import com.jorji.Item;
import com.jorji.ability.*;
import com.jorji.armor.ArmorClass;
import com.jorji.armor.ArmorClassCalculator;
import com.jorji.stat.Speed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@AllArgsConstructor
@Getter
@Setter
public class Hero {
    private String raceId;
    private Speed speed;
    private int level;
    private boolean hasShield;
    private final ArmorClass armorClass = new ArmorClass();
    private final Map<Ability, AbilityScore> abilityScores = new EnumMap<>(Ability.class);
    private final Set<Skill> skillProficiencies = EnumSet.noneOf(Skill.class);  //Навыки
    private final Set<Ability> savingThrowProficiencies = EnumSet.noneOf(Ability.class); //Спасброски
    private final Map<EquipmentSlot, Item> equippedItems = new HashMap<>();

    {
        abilityScores.put(Ability.CHARISMA, new AbilityScore(0));
        abilityScores.put(Ability.CONSTITUTION, new AbilityScore(0));
        abilityScores.put(Ability.DEXTERITY, new AbilityScore(0));
        abilityScores.put(Ability.STRENGTH, new AbilityScore(0));
        abilityScores.put(Ability.WISDOM, new AbilityScore(0));
        abilityScores.put(Ability.INTELLIGENCE, new AbilityScore(0));
    }

    public void grantSkillProficiency(Skill skill) {
        skillProficiencies.add(skill);
    }

    public void revokeSkillProficiency(Skill skill) {
        skillProficiencies.remove(skill);
    }

    public boolean hasSkillProficiency(Skill skill) {
        return skillProficiencies.contains(skill);
    }

    public int getAbilityModifier(Ability ability){
        return AbilityMath.modifierFor(abilityScores.get(ability).effectiveValue());
    }

    public int getSkillModifier(Skill skill){
        int abilityModifier = getAbilityModifier(skill.getGoverningAbility());
        return skillProficiencies.contains(skill)
                ? abilityModifier + ProficiencyBonus.forLevel(level)
                : abilityModifier;
    }

    public boolean hasShield(){
        return hasShield;
    }

    public int getArmorClassValue(ArmorClassCalculator calculator) {
        int computed = calculator.calculate(this, Optional.ofNullable(equippedItems.get(EquipmentSlot.ARMOR)), hasShield());
        return armorClass.effectiveValue(computed);
    }
}
