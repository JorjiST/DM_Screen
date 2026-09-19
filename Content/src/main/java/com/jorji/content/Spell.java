package com.jorji.content;

import com.jorji.content.enums.SpellComponent;

import java.util.Set;

public record Spell(
        String id,
        String name,
        String description,
        String castingTime, //Action, Bonus Action
        String distanceInFoots,
        String duration, //Concentration, Immediately
        int level,
        Set<SpellComponent> components
){}
