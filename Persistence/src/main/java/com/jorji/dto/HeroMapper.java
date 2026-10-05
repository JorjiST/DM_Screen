package com.jorji.dto;

import com.jorji.content.Feat;
import com.jorji.content.Item;
import com.jorji.content.Peculiarity;
import com.jorji.content.Spell;
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

    public Hero fromDTO(HeroDTO heroDTO) {
        return null;
    }
}
