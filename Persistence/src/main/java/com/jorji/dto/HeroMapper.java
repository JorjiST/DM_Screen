package com.jorji.dto;

import com.jorji.ContentRegistry;
import com.jorji.content.Feat;
import com.jorji.content.Item;
import com.jorji.content.Peculiarity;
import com.jorji.content.Spell;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.hero.Hero;
import com.jorji.hero.HeroDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public final class HeroMapper {

    public HeroDTO toDTO(Hero hero) {
        Map<String, String> equippedItems = hero.getEquippedItems().entrySet()
                .stream()
                .collect(Collectors.toMap(
                        k -> k.getKey().toString(),
                        e -> e.getValue().getId()
                ));
        List<String> inventory = hero.getInventory()
                .stream()
                .map(Item::getId)
                .toList();

        Set<String> feats = hero.getFeats().stream().map(Feat::id).collect(Collectors.toSet());
        Set<String> spells = hero.getSpells().stream().map(Spell::id).collect(Collectors.toSet());
        Set<String> peculiarities = hero.getPeculiarities().stream().map(Peculiarity::id).collect(Collectors.toSet());

        return new HeroDTO(
                hero.getName(),
                hero.getRaceId(),
                hero.getClassId(),
                hero.getSpeed(),
                hero.getLevel(),
                hero.getHitPoint(),
                hero.hasShield(),
                hero.getArmorClass(),
                hero.getSkillProficienciesFromContent(),
                hero.getSavingThrowProficienciesFromContent(),
                hero.getManualSkillProficiencies(),
                hero.getManualSavingThrowProficiencies(),
                equippedItems,
                inventory,
                feats,
                spells,
                peculiarities,
                hero.getAbilityScores()
        );
    }

    public Hero toEntity(HeroDTO dto, ContentRegistry contentRegistry) {
        Map<EquipmentSlot, Item> equippedItems = dto.getEquippedItems().entrySet().stream()
                .collect(Collectors.toMap(
                        k -> EquipmentSlot.valueOf(k.getKey()),
                        v -> contentRegistry.getItem(v.getValue())
                ));

        List<Item> inventory = dto.getInventory().stream().map(contentRegistry::getItem).toList();
        Set<Feat> feats = dto.getFeats().stream().map(contentRegistry::getFeat).collect(Collectors.toSet());
        Set<Spell> spells = dto.getSpells().stream().map(contentRegistry::getSpell).collect(Collectors.toSet());
        Set<Peculiarity> peculiarities = dto.getPeculiarities()
                .stream().map(contentRegistry::getPeculiarity).collect(Collectors.toSet());

        Hero hero = new Hero(
                dto.getName(),
                dto.getRaceId(),
                dto.getClassId(),
                dto.getSpeed(),
                dto.getLevel(),
                dto.getHitPoint(),
                dto.isHasShield(),
                dto.getArmorClass()
        );
        hero.getSkillProficienciesFromContent().addAll(dto.getSkillProficienciesFromContent());
        hero.getManualSkillProficiencies().addAll(dto.getManualSkillProficiencies());
        hero.getSavingThrowProficienciesFromContent().addAll(dto.getSavingThrowProficienciesFromContent());
        hero.getManualSavingThrowProficiencies().addAll(dto.getManualSavingThrowProficiencies());
        hero.getEquippedItems().putAll(equippedItems);
        hero.getInventory().addAll(inventory);
        hero.getFeats().addAll(feats);
        hero.getSpells().addAll(spells);
        hero.getPeculiarities().addAll(peculiarities);
        hero.getAbilityScores().putAll(dto.getAbilities());
        return hero;
    }
}
