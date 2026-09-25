package com.jorji.modifier;

import com.jorji.ContentRegistry;
import com.jorji.hero.Hero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PeculiarityService {
    private ContentRegistry contentRegistry;

    public void grant(Hero hero, String peculiarityId){
        hero.grantPeculiarity(contentRegistry.getPeculiarity(peculiarityId));
    }

    public void revoke(Hero hero, String peculiarityId){
        hero.revokePeculiarity(contentRegistry.getPeculiarity(peculiarityId));
    }
}
