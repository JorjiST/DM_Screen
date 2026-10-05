package com.jorji.modifier;

import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import java.util.List;

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

    public void recalculate(Hero hero) {
        recalculate(hero, hero.getHeroModifierSources());
    }

    private void resetToBase(Hero hero) {
        log.info("Resetting hero to base values: " + hero.getName());
        for (AbilityScore score : hero.getAbilityScores().values()) {
            score.resetToBase();
        }
        hero.getSpeed().resetToBase();
        hero.getArmorClass().resetToBase();
        hero.getHitPoint().resetToBase();
        hero.clearSkillProficienciesFromContent();
        hero.clearSavingThrowProficienciesFromContent();
    }
}
