package com.jorji.ability;

public final class ProficiencyBonus {
    private ProficiencyBonus() {}

    public static int forLevel(int level) {
        return 2 + (level - 1) / 4;
    }
}
