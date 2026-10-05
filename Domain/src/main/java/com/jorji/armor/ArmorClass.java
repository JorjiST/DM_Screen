package com.jorji.armor;

import com.jorji.stat.ComputedStat;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class ArmorClass extends ComputedStat {

    public ArmorClass(int base) {
        super(base);
    }
}
