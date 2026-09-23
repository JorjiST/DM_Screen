package com.jorji.modifier;

import com.jorji.content.enums.Operation;

public record Modifier(String target, Operation operation, Object value) {

}
