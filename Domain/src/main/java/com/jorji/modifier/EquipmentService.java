package com.jorji.modifier;

import com.jorji.ContentRegistry;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.content.Item;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class EquipmentService {
    private final HeroRecalculationService recalculationService;
    private final ContentRegistry contentRegistry;

    public void equip(Hero hero, Item item, EquipmentSlot slot) {
        hero.equipItem(item, slot);
        recalculate(hero);
    }

    public void unequip(Hero hero, EquipmentSlot slot) {
        hero.unequipItem(slot);
        recalculate(hero);
    }

    private void recalculate(Hero hero) {
        List<ModifierSource> sources = new ArrayList<>();
        sources.add(contentRegistry.getRace(hero.getRaceId()));
        sources.add(contentRegistry.getCharacterClass(hero.getClassId()));
        sources.addAll(hero.getEquippedItems().values());

        recalculationService.recalculate(hero, sources);
    }
}
