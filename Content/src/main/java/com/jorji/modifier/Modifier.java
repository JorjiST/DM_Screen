package com.jorji.modifier;

import com.jorji.Operation;

public record Modifier(String target, Operation operation, Object value) {

}
