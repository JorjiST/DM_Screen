package com.jorji.hero;

import com.jorji.content.*;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.ability.*;
import com.jorji.armor.ArmorClass;
import com.jorji.armor.ArmorClassCalculator;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Hero {
    private String name;
    private String raceId;
    private String classId;
    private Speed speed;
    private int level;
    private HitPoint hitPoint;
    private boolean hasShield;
    private ArmorClass armorClass;
    private final Set<Skill> skillProficienciesFromContent = EnumSet.noneOf(Skill.class);  //Работает только с хэндлерами
    private final Set<Ability> savingThrowProficienciesFromContent = EnumSet.noneOf(Ability.class);
    private final Set<Skill> manualSkillProficiencies = EnumSet.noneOf(Skill.class);  //Ручное изменение, по образу с ComputedStat
    private final Set<Ability> manualSavingThrowProficiencies = EnumSet.noneOf(Ability.class);
    private final Map<EquipmentSlot, Item> equippedItems = new EnumMap<>(EquipmentSlot.class);
    private final List<Item> inventory = new ArrayList<>();
    private final Set<Feat> feats = new HashSet<>();
    private final Set<Spell> spells = new HashSet<>();
    private final Set<Peculiarity> peculiarities = new HashSet<>();
    private final Map<Ability, AbilityScore> abilityScores = new EnumMap<>(Ability.class);

    {
        for (Ability ability : Ability.values()) {
            abilityScores.put(ability, new AbilityScore(10));
        }
    }

    // API для Handler`ов
    public void grantSkillProficiencyFromContent(Skill skill) {
        skillProficienciesFromContent.add(skill);
    }

    public void revokeSkillProficiencyFromContent(Skill skill) {
        skillProficienciesFromContent.remove(skill);
    }

    public boolean hasSkillProficiencyFromContent(Skill skill) {
        return skillProficienciesFromContent.contains(skill);
    }

    public void grantSavingThrowProficiencyFromContent(Ability ability) {
        savingThrowProficienciesFromContent.add(ability);
    }

    public void revokeSavingThrowProficiencyFromContent(Ability ability) {
        savingThrowProficienciesFromContent.remove(ability);
    }

    public boolean hasSavingThrowProficiencyFromContent(Ability ability) {
        return savingThrowProficienciesFromContent.contains(ability);
    }

    public void clearSkillProficienciesFromContent() {
        skillProficienciesFromContent.clear();
    }

    public void clearSavingThrowProficienciesFromContent() {
        savingThrowProficienciesFromContent.clear();
    }

    // API для ручного изменения ДМ`ом
    public void grantSkillProficiency(Skill skill) {
        manualSkillProficiencies.add(skill);
    }

    public void revokeSkillProficiency(Skill skill) {
        manualSkillProficiencies.remove(skill);
    }

    public void grantSavingThrowProficiency(Ability ability) {
        manualSavingThrowProficiencies.add(ability);
    }

    public void revokeSavingThrowProficiency(Ability ability) {
        manualSavingThrowProficiencies.remove(ability);
    }

    public boolean hasSavingThrowProficiency(Ability ability) {
        return manualSavingThrowProficiencies.contains(ability) || savingThrowProficienciesFromContent.contains(ability);
    }

    public boolean hasSkillProficiency(Skill skill) {
        return manualSkillProficiencies.contains(skill) || skillProficienciesFromContent.contains(skill);
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItemToInventory(Item item) {
        inventory.remove(item);
    }

    public void grantPeculiarity(Peculiarity peculiarity){
        peculiarities.add(peculiarity);
    }

    public void revokePeculiarity(Peculiarity peculiarity){
        peculiarities.remove(peculiarity);
    }

    public void grantFeat(Feat feat){
        feats.add(feat);
    }

    public void revokeFeat(Feat feat){
        feats.remove(feat);
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
        return hasSkillProficiency(skill)
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
