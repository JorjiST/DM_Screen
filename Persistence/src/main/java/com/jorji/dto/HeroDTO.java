package com.jorji.dto;

import com.jorji.ability.Ability;
import com.jorji.ability.AbilityScore;
import com.jorji.ability.Skill;
import com.jorji.armor.ArmorClass;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class HeroDTO {
    private String name;
    private String raceId;
    private String classId;
    private Speed speed;
    private int level;
    private HitPoint hitPoint;
    private boolean hasShield;
    private ArmorClass armorClass;
    private Set<Skill> skillProficienciesFromContent;
    private Set<Ability> savingThrowProficienciesFromContent;
    private Set<Skill> manualSkillProficiencies;
    private Set<Ability> manualSavingThrowProficiencies;
    private Map<String, String> equippedItems;
    private List<String> inventory;
    private Set<String> feats;
    private Set<String> spells;
    private Set<String> peculiarities;
    private Map<Ability, AbilityScore> abilities;
}
