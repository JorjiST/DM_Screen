package com.jorji.stat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HitPoint extends ComputedStat{
    private int tempHitPoints;

    public HitPoint(int base) {
        super(base);
    }

    public int getGeneralHealth(){
        return getComputed() + tempHitPoints;
    }
}
