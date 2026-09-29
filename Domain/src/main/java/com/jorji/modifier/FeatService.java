package com.jorji.modifier;

import com.jorji.ContentRegistry;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FeatService {
    private final ContentRegistry contentRegistry;

    public void grant(Hero hero, String id){
        hero.grantFeat(contentRegistry.getFeat(id));
    }

    public void revoke(Hero hero, String id){
        hero.revokeFeat(contentRegistry.getFeat(id));
    }
}
