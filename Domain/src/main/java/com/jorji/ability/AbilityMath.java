package com.jorji.ability;

public final class AbilityMath {
    private AbilityMath() {}

    public static int modifierFor(int abilityScore) {
        return Math.floorDiv(abilityScore - 10, 2);
    }
}
