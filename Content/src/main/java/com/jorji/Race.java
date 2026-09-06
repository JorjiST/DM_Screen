package com.jorji;

import com.jorji.modifier.Modifier;

import java.util.List;

public record Race(String id, String name, int baseSpeed, List<Modifier> modifiers) {

}
