package modifier;

import com.jorji.Character.Hero;
import com.jorji.modifier.Modifier;

public interface ModifierHandler {
    void register();
    void apply(Hero hero, Modifier modifier);
}