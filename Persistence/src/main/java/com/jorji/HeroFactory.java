package com.jorji;

import com.jorji.hero.Hero;
import com.jorji.hero.HeroDTO;
import com.jorji.modifier.HeroRecalculationService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HeroFactory { //Нужен ли вообще?
    private final HeroRecalculationService recalculationService;
    private final ContentRegistry contentRegistry;
    private final HeroMapper heroMapper;

    public Hero createHero(HeroDTO heroDTO) {
        Hero hero = heroMapper.toEntity(heroDTO, contentRegistry);
        recalculationService.recalculate(hero);
        return hero;
    }
}
