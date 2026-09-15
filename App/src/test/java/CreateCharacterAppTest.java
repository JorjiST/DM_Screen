 import com.jorji.ability.Ability;
 import com.jorji.hero.Hero;
 import com.jorji.ModifierEngine;
 import com.jorji.content.Race;
 import com.jorji.loader.ContentLoader;
 import com.jorji.modifier.handler.AbilityModifierHandler;
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
         AbilityModifierHandler abilityModifierHandler = new AbilityModifierHandler();
         modifierEngine.registerModifierHandler(abilityModifierHandler);

         Path path = Path.of(getClass().getResource("/elf.json").toURI());
         Race race = contentLoader.loadRace(path);
         Hero hero = new Hero(race.id(), "classId", new Speed(race.baseSpeed()), 1, false);
         modifierEngine.apply(hero, race.modifiers());

         assertEquals(hero.getRaceId(), race.id());
         assertEquals(race.baseSpeed(), hero.getSpeed().effectiveValue());
         assertEquals(2, hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue());
         assertEquals(10, hero.getAbilityScores().get(Ability.STRENGTH).effectiveValue());

     }
 }
