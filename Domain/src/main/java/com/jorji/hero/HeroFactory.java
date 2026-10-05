package com.jorji.hero;

import com.jorji.ContentRegistry;
import com.jorji.modifier.HeroRecalculationService;
import com.jorji.modifier.ModifierSource;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class HeroFactory {
    private final HeroRecalculationService recalculationService;
    private final ContentRegistry contentRegistry;

    public Hero createHero(HeroDTO heroDTO) {

    }
}
