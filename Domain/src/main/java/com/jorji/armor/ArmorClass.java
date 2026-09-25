package com.jorji.armor;

import com.jorji.stat.ComputedStat;

import lombok.Setter;

@Setter
public class ArmorClass extends ComputedStat {

    public ArmorClass(int base) {
        super(base);
    }
}
