import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.content.Armor;
import com.jorji.content.Item;
import com.jorji.content.Weapon;
import com.jorji.content.enums.ArmorCategory;
import com.jorji.content.enums.EquipmentSlot;
import com.jorji.content.enums.Operation;
import com.jorji.modifier.Modifier;
import com.jorji.repository.ItemRepository;

public class ItemRepositoryTest {
    
    @Test
    void savesItem(){
        ItemRepository itemRepository = new ItemRepository(new ObjectMapper());
        Item testItem = new Item(
            "sword", "Меч", "Пиздатый меч", EquipmentSlot.HAND,
             List.of(new Modifier("ability.dextrecity", Operation.ADD, 4)));

        Armor armor = new Armor(
            "armor", "Броня", "Пиздатая броня", 
            List.of(new Modifier("ability.constitution", Operation.ADD, 4)),
        ArmorCategory.HEAVY, 18, 15);

        Weapon weapon = new Weapon(
            "weapon", "Оружие", "Пиздатое оружие", 
            EquipmentSlot.HAND, List.of(new Modifier("ability.strength", Operation.ADD, 4)), null, null, 0);
        itemRepository.save(testItem);
        itemRepository.save(armor);
        itemRepository.save(weapon);
    }
}
