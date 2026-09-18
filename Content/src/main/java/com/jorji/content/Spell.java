package com.jorji.content;

import com.jorji.content.enums.SpellComponents;

public record Spell(
        String id,
        String name,
        String description,
        String castingTime, //Action, Bonus Action
        String distanceInFoots,
        String duration, //Concentration, Immediately
        int level,
        SpellComponents components
){}
