package com.jorji.stat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HitPoint extends ComputedStat {
    private int tempHitPoints;

    public HitPoint(int base) {
        super(base);
    }

    @JsonIgnore
    public int getGeneralHealth() {
        return getComputed() + tempHitPoints;
    }

    public int takeDamage(int value) {
        if (value > 0) {
            if (getGeneralHealth() < value) {
                setComputed(0);
                tempHitPoints = 0;
                return getGeneralHealth();
            } else {
                if (tempHitPoints > value) {
                    tempHitPoints -= value;
                    return getGeneralHealth();
                } else {
                    value -= tempHitPoints;
                    tempHitPoints = 0;
                    setComputed(getComputed() - value);
                }
            }
            setComputed(getComputed() - value);
            return getGeneralHealth();
        } else {
            throw new IllegalArgumentException("Value must be positive");
        }
    }

    public int heal(int value) {
        if (value > 0) {
            if (getComputed() + value > getBase()) {
                resetToBase();
            } else {
                setComputed(getComputed() + value);
            }
            return getGeneralHealth();
        } else {
            throw new IllegalArgumentException("Value must be positive");
        }
    }
}
