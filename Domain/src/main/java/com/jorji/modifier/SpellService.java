package com.jorji.modifier;

import com.jorji.ContentRegistry;
import com.jorji.content.Spell;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SpellService {
    private final ContentRegistry contentRegistry;

    public void grantSpell(Hero hero, String spellId){
        hero.grandSpell(contentRegistry.getSpell(spellId));
    }

    public void revokeSpell(Hero hero, String spellId){
        hero.revokeSpell(contentRegistry.getSpell(spellId));
    }
}
