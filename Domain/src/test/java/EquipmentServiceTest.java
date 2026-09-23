import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import com.jorji.armor.ArmorClassCalculator;
import com.jorji.content.Armor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.jorji.ContentRegistry;
import com.jorji.modifier.ModifierEngine;
import com.jorji.modifier.*;
import com.jorji.ability.Ability;
import com.jorji.content.enums.EquipmentSlot;
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
        recalculationService.recalculate(hero,
                List.of(contentRegistry.getRace(hero.getRaceId()), contentRegistry.getCharacterClass(hero.getClassId())));
    }

    @Test
    void modifiersApplyAfterEquip(){
        int startedScore = hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue();
        int bonusScore = (Integer) contentRegistry.getItem("ring").modifiers().getFirst().value();
        equipmentService.equip(hero, "ring");
        assertEquals(startedScore + bonusScore, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }

    @Test
    void modifiersRemoveAfterUnequip() {
        modifiersApplyAfterEquip();
        int startedScore = hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue();
        int bonusScore = (Integer) contentRegistry.getItem("ring").modifiers().getFirst().value();
        equipmentService.unequip(hero, EquipmentSlot.HAND);
        assertEquals(startedScore - bonusScore, hero.getAbilityScores().get(Ability.CHARISMA).effectiveValue());
    }

    @Test
    void armorModifiers(){
        ArmorClassCalculator calculator = new ArmorClassCalculator();
        Armor armor = (Armor) contentRegistry.getItem("armor");
        int armorClassBonus = armor.getBaseArmorClass();
        int dexHeroModifier = hero.getAbilityModifier(Ability.DEXTERITY);
        equipmentService.equip(hero, "armor");
        assertEquals(dexHeroModifier + armorClassBonus, hero.getArmorClassValue(calculator));
    }
}