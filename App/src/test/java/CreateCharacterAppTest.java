 import com.jorji.ContentRegistry;
 import com.jorji.ability.Ability;
 import com.jorji.ability.Skill;
 import com.jorji.content.CharacterClass;
 import com.jorji.hero.Hero;
 import com.jorji.modifier.HeroRecalculationService;
 import com.jorji.modifier.ModifierEngine;
 import com.jorji.content.Race;
 import com.jorji.modifier.handler.DefaultModifierHandlers;
 import com.jorji.stat.HitPoint;
 import com.jorji.stat.Speed;
 import org.junit.jupiter.api.Test;
 import java.io.IOException;
 import java.net.URISyntaxException;
 import java.nio.file.Path;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertTrue;

 public class CreateCharacterAppTest {

     @Test
     void readAndCreateAndAddModifierTest() throws IOException, URISyntaxException {
         ModifierEngine modifierEngine = new ModifierEngine();
         DefaultModifierHandlers handlers = new DefaultModifierHandlers();
         ContentRegistry contentRegistry = new ContentRegistry();
         HeroRecalculationService heroRecalculationService = new HeroRecalculationService(modifierEngine);
         Path path = Path.of(getClass().getResource("/classes").toURI());
         Path rootPath = path.getParent();
         contentRegistry.loadAll(rootPath);

         modifierEngine.registerModifierHandler(handlers.all());

//         Path racePath = Path.of(getClass().getResource("/elf.json").toURI());
//         Path classPath = Path.of(getClass().getResource("/fighter.json").toURI());

         Race race = contentRegistry.getRace("elf");
         CharacterClass characterClass = contentRegistry.getCharacterClass("fighter");
         Hero hero = new Hero("Alan", race.id(), characterClass.id(), new Speed(race.baseSpeed()), 1,  new HitPoint(0),false);

         int startedStrengthValue = hero.getAbilityScores().get(Ability.STRENGTH).effectiveValue();
         int startedDexterityValue = hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue();
         int dexterityBonus = (Integer) contentRegistry.getRace(hero.getRaceId()).modifiers().getFirst().value();
         int strengthBonus = (Integer) contentRegistry.getCharacterClass(hero.getClassId()).modifiers().getFirst().value();
         heroRecalculationService.recalculate(hero, List.of(race, characterClass));

         assertEquals(hero.getRaceId(), race.id());
         assertEquals(race.baseSpeed(), hero.getSpeed().effectiveValue());
         assertEquals(hero.getClassId(), characterClass.id());
         assertTrue(hero.hasSavingThrowProficiency(Ability.STRENGTH));
         assertTrue(hero.hasSkillProficiency(Skill.ARCANA));
         assertEquals(startedDexterityValue + dexterityBonus, hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue());
         assertEquals(startedStrengthValue + strengthBonus, hero.getAbilityScores().get(Ability.STRENGTH).effectiveValue());
     }
 }
