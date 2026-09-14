package com.jorji.stat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputedStat {
    private final int base;
    private int computed;
    private Integer override;

    public ComputedStat(int base) {
        this.base = base;
        this.computed = base;
    }

    public int effectiveValue() {
        return override != null ? override : computed;
    }

    public void resetToBase() {
        this.computed = base;
    }
}
