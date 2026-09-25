package com.jorji.modifier;

import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

import java.util.List;
import java.util.Set;

@Log4j
@AllArgsConstructor
public class HeroRecalculationService {
    private final ModifierEngine modifierEngine;

    public void recalculate(Hero hero, List<ModifierSource> activeSources) {
        log.info("Recalculating hero: " + hero.getName() + " with active sources: " + activeSources);
        resetToBase(hero);

        for (ModifierSource source : activeSources) {
            modifierEngine.apply(hero, source.modifiers());
        }
    }

    private void resetToBase(Hero hero) {
        log.info("Resetting hero to base values: " + hero.getName());
        for (AbilityScore score : hero.getAbilityScores().values()) {
            score.resetToBase();
        }
        hero.getSpeed().resetToBase();
        hero.getArmorClass().resetToBase();
        hero.getHitPoint().resetToBase();
        // hero.getSkillProficienciesFromContent().clear();      — после разделения Set (roadmap, шаг 5, ещё не сделан)
        // hero.getSavingThrowProficienciesFromContent().clear();
    }
}
