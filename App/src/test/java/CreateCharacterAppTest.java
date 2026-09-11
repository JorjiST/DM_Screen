 import com.jorji.Ability.Ability;
 import com.jorji.Character.Hero;
 import com.jorji.ModifierEngine;
 import com.jorji.Race;
 import com.jorji.loader.ContentLoader;
 import com.jorji.modifier.AbilityModifierHandler;
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
         AbilityModifierHandler abilityModifierHandler = new AbilityModifierHandler(modifierEngine);
         abilityModifierHandler.register();

         Path path = Path.of(getClass().getResource("/elf.json").toURI());
         Race race = contentLoader.loadRace(path);
         Hero hero = new Hero(race.id(), race.baseSpeed(), 1);
         modifierEngine.apply(hero, race.modifiers());

         assertEquals(hero.getRaceId(), race.id());
         assertEquals(race.baseSpeed(), hero.getSpeed());
         assertEquals(2, hero.getAbilityScores().get(Ability.DEXTERITY).effectiveValue());
         assertEquals(10, hero.getAbilityScores().get(Ability.STRENGTH).effectiveValue());

     }
 }
