package com.jorji;

import com.jorji.ability.Ability;
import com.jorji.hero.Hero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HeroViewController {
    @FXML private Label nameLabel;
    @FXML private Label strengthLabel;
    @FXML private Label dexterityLabel;
    @FXML private Label speedLabel;

    public void setHero(Hero hero) {
        nameLabel.setText(hero.getName());
        strengthLabel.setText("СИЛ: " + hero.getAbilityModifier(Ability.STRENGTH));
        dexterityLabel.setText("ЛОВ: " + hero.getAbilityModifier(Ability.DEXTERITY));
        speedLabel.setText("Скорость: " + hero.getSpeed().effectiveValue());
    }
}