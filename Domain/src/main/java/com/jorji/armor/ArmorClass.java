package com.jorji.armor;

import lombok.Setter;

@Setter
public class ArmorClass {
    private Integer override;

    public int effectiveValue(int computed) {
        return override != null ? override : computed;
    }
}
