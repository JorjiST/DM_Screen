 import com.jorji.ability.Ability;
 import com.jorji.ability.Skill;
 import com.jorji.content.CharacterClass;
 import com.jorji.hero.Hero;
 import com.jorji.modifier.ModifierEngine;
 import com.jorji.content.Race;
 import com.jorji.loader.ContentLoader;
 import com.jorji.modifier.handler.DefaultModifierHandlers;
 import com.jorji.stat.Speed;
 import org.junit.jupiter.api.Test;

 import java.io.IOException;
 import java.net.URISyntaxException;
 import java.nio.file.Path;

 import static org.junit.jupiter.api.Assertions.assertEquals;

 public class CreateCharacterAppTest {

     @Test
     void readAndCreateAndAddModifierTest() throws IOException, URISyntaxException {
         ContentLoader contentLoader = new ContentLoader();
         ModifierEngine modifierEngine = new ModifierEngine();
         DefaultModifierHandlers handlers = new DefaultModifierHandlers();
         modifierEngine.registerModifierHandler(handlers.all());

         Path racePath = Path.of(getClass().getResource("/elf.json").toURI());
         Path classPath = Path.of(getClass().getResource("/fighter.json").toURI());

         Race race = contentLoader.loadRace(racePath);
         CharacterClass characterClass = contentLoader.loadClass(classPath);
         Hero hero = new Hero("Alan", race.id(), characterClass.id(), new Speed(race.baseSpeed()), 1, false);

         modifierEngine.apply(hero, race.modifiers());
         modifierEngine.apply(hero, characterClass.modifiers());

         assertEquals(hero.getRaceId(), race.id());
         assertEquals(race.baseSpeed(), hero.getSpeed().effectiveValue());
         assertEquals(hero.getClassId(), characterClass.id());
         assertEquals(true, hero.hasSavingThrowProficiency(Ability.STRENGTH));
         assertEquals(true, hero.hasSkillProficiency(Skill.ARCANA));
         assertEquals(2, hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue());
         assertEquals(110, hero.getAbilityScores().get(Ability.STRENGTH).effectiveValue());
     }
 }
