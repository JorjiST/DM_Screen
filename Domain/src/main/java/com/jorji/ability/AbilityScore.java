package com.jorji.ability;

import com.jorji.stat.ComputedStat;

public class AbilityScore extends ComputedStat {

    public int effectiveValue(){
        return getOverride() != null ? getOverride() : getComputed();
    }
    
    public AbilityScore(int base){
        super(base);
    }
}
