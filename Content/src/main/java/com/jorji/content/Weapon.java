package com.jorji.content;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jorji.content.enums.DamageType;
import com.jorji.content.enums.Dice;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.content.enums.WeaponProperty;
import com.jorji.modifier.Modifier;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Weapon extends Item {
    private final DamageType damageType;
    private final Dice dice;
    private final int countOfDice;
    private final Set<WeaponProperty> properties = new HashSet<>();

    public Weapon(@JsonProperty("id") String id,
                  @JsonProperty("name") String name,
                  @JsonProperty("description") String description,
                  @JsonProperty("equipmentSlot") EquipmentSlot equipmentSlot,
                  @JsonProperty("modifiers") List<Modifier> modifiers,
                  @JsonProperty("damageType") DamageType damageType,
                  @JsonProperty("dice") Dice dice,
                  @JsonProperty("countOfDice") int countOfDice) {
        super(id, name, description, equipmentSlot, modifiers);
        this.damageType = damageType;
        this.dice = dice;
        this.countOfDice = countOfDice;
    }
}
