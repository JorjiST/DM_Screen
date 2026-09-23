package com.jorji.modifier;

import com.jorji.content.enums.Operation;

public final class ModifierMath {
    private ModifierMath() {}

    public static int requireIntValue(Modifier modifier) {
        if (!(modifier.value() instanceof Integer amount)) {
            throw new IllegalArgumentException(
                    "Expected integer value, got: " + modifier.value());
        }
        return amount;
    }

    public static int applyIntOperation(Operation operation, int current, int amount) {
        return switch (operation) {
            case ADD -> current + amount;
            case SET -> amount;
            case GRANT -> throw new UnsupportedOperationException(
                    "GRANT is not applicable to numeric fields");
        };
    }
}
