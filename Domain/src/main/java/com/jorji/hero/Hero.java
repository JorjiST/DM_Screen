package com.jorji.hero;

import com.jorji.content.Spell;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.content.Item;
import com.jorji.ability.*;
import com.jorji.content.Armor;
import com.jorji.armor.ArmorClass;
import com.jorji.armor.ArmorClassCalculator;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Hero {
    private String name;
    private String raceId;
    private String classId;
    private Speed speed;
    private int level;
    private HitPoint hitPoint;
    private boolean hasShield;
    private final ArmorClass armorClass = new ArmorClass(0);
    private final Set<Skill> skillProficiencies = EnumSet.noneOf(Skill.class);  //Навыки
    private final Set<Ability> savingThrowProficiencies = EnumSet.noneOf(Ability.class); //Спасброски
    private final Map<EquipmentSlot, Item> equippedItems = new EnumMap<>(EquipmentSlot.class);
    private final List<Item> inventory = new ArrayList<>();
    private final Set<Spell> spells = new HashSet<>();
    private final Map<Ability, AbilityScore> abilityScores = Map.of(
            Ability.CHARISMA, new AbilityScore(10),
            Ability.CONSTITUTION, new AbilityScore(10),
            Ability.DEXTERITY, new AbilityScore(10),
            Ability.STRENGTH, new AbilityScore(10),
            Ability.WISDOM, new AbilityScore(10),
            Ability.INTELLIGENCE, new AbilityScore(10)
    );

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

    public void grandSpell(Spell spell){
        spells.add(spell);
    }

    public void revokeSpell(Spell spell){
        spells.remove(spell);
    }

    public void equipItem(Item item) {
        EquipmentSlot slot = item.getEquipmentSlot();
        if (equippedItems.containsKey(slot)) {
            inventory.add(equippedItems.get(slot));
        }
        equippedItems.put(slot, item);
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
        return armorClass.getOverride() != null ? armorClass.getOverride() : computed;
    }
}
