import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jorji.ContentRegistry;
import com.jorji.modifier.ModifierEngine;
import com.jorji.modifier.*;
import com.jorji.ability.Ability;
import com.jorji.content.EquipmentSlot;
import com.jorji.hero.Hero;
import com.jorji.loader.ContentLoader;
import com.jorji.modifier.handler.DefaultModifierHandlers;
import com.jorji.stat.Speed;

public class EquipmentServiceTest {

    private ContentLoader contentLoader = new ContentLoader();
    private EquipmentService equipmentService;
    private ContentRegistry contentRegistry;
    private Hero hero;

    @BeforeEach 
    void setUp() throws IOException, URISyntaxException{
        ModifierEngine modifierEngine = new ModifierEngine();
        DefaultModifierHandlers modifierHandlers = new DefaultModifierHandlers();
        modifierEngine.registerModifierHandler(modifierHandlers.all());

        HeroRecalculationService recalculationService = new HeroRecalculationService(modifierEngine);
        contentRegistry = new ContentRegistry();

        equipmentService = new EquipmentService(recalculationService, contentRegistry);
        
        Path path = Path.of(getClass().getResource("/classes").toURI());
        Path rootPath = path.getParent();
        contentRegistry.loadAll(contentLoader, rootPath);

        hero = new Hero("Alan", "elf", "fighter", new Speed(10), 1, false);
    }

    @Test
    void modifiersApplyAfterEquip(){
        assertEquals(0, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());

        equipmentService.equip(hero, contentRegistry.getItem("ring"), EquipmentSlot.HAND);

        assertEquals(2, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }

    @Test
    void modifiersRemoveAfterUnequip() {
        equipmentService.equip(hero, contentRegistry.getItem("ring"), EquipmentSlot.HAND);
        assertEquals(2, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
        equipmentService.unequip(hero, EquipmentSlot.HAND);
        assertEquals(0, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }
}