package com.jorji.hero;

import com.jorji.content.EquipmentSlot;
import com.jorji.content.Item;
import com.jorji.ability.*;
import com.jorji.content.Armor;
import com.jorji.armor.ArmorClass;
import com.jorji.armor.ArmorClassCalculator;
import com.jorji.stat.Speed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Hero {
    private String raceId;
    private String classId;
    private Speed speed;
    private int level;
    private boolean hasShield;
    private final ArmorClass armorClass = new ArmorClass();
    private final Map<Ability, AbilityScore> abilityScores = new EnumMap<>(Ability.class);
    private final Set<Skill> skillProficiencies = EnumSet.noneOf(Skill.class);  //Навыки
    private final Set<Ability> savingThrowProficiencies = EnumSet.noneOf(Ability.class); //Спасброски
    private final Map<EquipmentSlot, Item> equippedItems = new HashMap<>();
    private final List<Item> inventory = new ArrayList<>();

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

    public void grantSavingThrowProficiency(Ability ability) {
        savingThrowProficiencies.add(ability);
    }

    public void revokeSavingThrowProficiency(Ability ability) {
        savingThrowProficiencies.remove(ability);
    }

    public boolean hasSavingThrowProficiency(Ability ability) {
        return savingThrowProficiencies.contains(ability);
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItemToInventory(Item item) {
        inventory.remove(item);
    }

    public void equipItem(Item item, EquipmentSlot equipmentSlot) {
        if (equippedItems.containsKey(equipmentSlot)) {
            inventory.add(equippedItems.get(equipmentSlot));
        }
        equippedItems.put(equipmentSlot, item);
        inventory.remove(item);
    }

    public void unequipItem(EquipmentSlot slot) {
        Item item = equippedItems.remove(slot);
        if (item != null) {
            inventory.add(item);
        }
    }

    public int getAbilityModifier(Ability ability) {
        return AbilityMath.modifierFor(abilityScores.get(ability).effectiveValue());
    }

    public int getSkillModifier(Skill skill) {
        int abilityModifier = getAbilityModifier(skill.getGoverningAbility());
        return skillProficiencies.contains(skill)
                ? abilityModifier + ProficiencyBonus.forLevel(level)
                : abilityModifier;
    }

    public boolean hasShield() {
        return hasShield;
    }

    public int getArmorClassValue(ArmorClassCalculator calculator) {
        int computed = calculator.calculate(this, Optional.ofNullable((Armor) equippedItems.get(EquipmentSlot.ARMOR)), hasShield());
        return armorClass.effectiveValue(computed);
    }
}
