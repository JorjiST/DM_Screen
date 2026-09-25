package com.jorji.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jorji.content.*;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.nio.file.Path;

@Log4j 
public class ContentLoader {

    private final ObjectMapper mapper = new JsonMapper();

    public Race loadRace(Path jsonRace) throws IOException {
        log.info("Loading race from: " + jsonRace);
        return mapper.readValue(jsonRace.toFile(), Race.class);
    }

    public CharacterClass loadClass(Path jsonClass) throws IOException {
        log.info("Loading class from: " + jsonClass);
        return mapper.readValue(jsonClass.toFile(), CharacterClass.class);
    }

    public Item loadItem(Path jsonItem) throws IOException {
        log.info("Loading item from: " + jsonItem);
        return mapper.readValue(jsonItem.toFile(), Item.class);
    }

    public Spell loadSpell(Path jsonSpell) throws IOException {
        log.info("Loading spell from: " + jsonSpell);
        return mapper.readValue(jsonSpell.toFile(), Spell.class);
    }

    public Peculiarity loadPeculiarity(Path jsonPeculiarity) throws IOException{
        log.info("Loading peculiarity from: " + jsonPeculiarity);
        return mapper.readValue(jsonPeculiarity.toFile(), Peculiarity.class);
    }
}
