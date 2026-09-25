package com.jorji.modifier;

import com.jorji.ContentRegistry;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpellService {
    private final ContentRegistry contentRegistry;

    public void grant(Hero hero, String spellId){
        hero.grandSpell(contentRegistry.getSpell(spellId));
    }

    public void revoke(Hero hero, String spellId){
        hero.revokeSpell(contentRegistry.getSpell(spellId));
    }
}
