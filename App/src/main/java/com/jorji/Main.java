package com.jorji;

import java.util.List;

import com.jorji.armor.ArmorClass;
import com.jorji.hero.Hero;
import com.jorji.modifier.EquipmentService;
import com.jorji.modifier.FeatService;
import com.jorji.modifier.HeroRecalculationService;
import com.jorji.modifier.ModifierEngine;
import com.jorji.modifier.PeculiarityService;
import com.jorji.modifier.SpellService;
import com.jorji.modifier.handler.DefaultModifierHandlers;
import com.jorji.stat.HitPoint;
import com.jorji.stat.Speed;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
    private ContentRegistry contentRegistry;
    private ModifierEngine modifierEngine;
    private SpellService spellService;
    private EquipmentService equipmentService;
    private FeatService featService;
    private HeroRecalculationService recalculationService;
    private PeculiarityService peculiarityService;

    public static void main(String[] args) {
      launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        initServices();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jorji/view/hero-view.fxml"));
        Parent root = loader.load();

        HeroViewController controller = loader.getController();
        Hero testHero = new Hero("Alan", "elf", "fighter", new Speed(30), 1, new HitPoint(10), false, new ArmorClass(10));
        recalculationService.recalculate(testHero, List.of(
            contentRegistry.getRace(testHero.getRaceId()),
            contentRegistry.getCharacterClass(testHero.getClassId())
        ));
        controller.setHero(testHero);

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("DM Screen");
        primaryStage.show();
    }

    private void initServices() {
        modifierEngine = new ModifierEngine();
        modifierEngine.registerModifierHandler(DefaultModifierHandlers.all());

        recalculationService = new HeroRecalculationService(modifierEngine);
        contentRegistry = new ContentRegistry();
        spellService = new SpellService(contentRegistry);
        equipmentService = new EquipmentService(recalculationService, contentRegistry);
        featService = new FeatService(contentRegistry);
        peculiarityService = new PeculiarityService(contentRegistry);
    }
}
