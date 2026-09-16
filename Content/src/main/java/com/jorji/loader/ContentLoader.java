package com.jorji.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jorji.content.Armor;
import com.jorji.content.CharacterClass;
import com.jorji.content.Item;
import com.jorji.content.Race;

import java.io.IOException;
import java.nio.file.Path;

public class ContentLoader {

    private final ObjectMapper mapper = new JsonMapper();

    public Race loadRace(Path jsonRace) throws IOException {
        return mapper.readValue(jsonRace.toFile(), Race.class);
    }

    public CharacterClass loadClass(Path jsonClass) throws IOException {
        return mapper.readValue(jsonClass.toFile(), CharacterClass.class);
    }

    public Item loadItem(Path jsonItem) throws IOException {
        return mapper.readValue(jsonItem.toFile(), Item.class);
    }

    public Armor loadArmor(Path jsonArmor) throws IOException {
        return mapper.readValue(jsonArmor.toFile(), Armor.class);
    }
}
