package com.jorji.modifier;

import com.jorji.ModifierEngine;
import com.jorji.ability.AbilityScore;
import com.jorji.hero.Hero;

import java.util.List;

//Вызывается в модуле App, в UI
public class HeroRecalculationService {
    private final ModifierEngine modifierEngine;

    public HeroRecalculationService(ModifierEngine modifierEngine) {
        this.modifierEngine = modifierEngine;
    }

    public void recalculate(Hero hero, List<ModifierSource> activeSources) {
        resetToBase(hero);

        for (ModifierSource source : activeSources) {
            modifierEngine.apply(hero, source.getModifiers());
        }
    }

    private void resetToBase(Hero hero) {
        for (AbilityScore score : hero.getAbilityScores().values()) {
            score.resetToBase();
        }
        hero.getSpeed().resetToBase();
        // hero.getSkillProficienciesFromContent().clear();      — после разделения Set (roadmap, шаг 5, ещё не сделан)
        // hero.getSavingThrowProficienciesFromContent().clear();
    }
}
