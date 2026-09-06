package com.jorji.Ability;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AbilityScore {
    private Integer computed;
    private Integer override;


    public int effectiveValue(){
        return override != null ? override : computed;
    }
    
    public AbilityScore(Integer computed){
        this.computed = computed;
    }
}
