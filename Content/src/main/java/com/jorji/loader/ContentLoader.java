package com.jorji.loader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jorji.Race;

import java.io.IOException;
import java.nio.file.Path;

public class ContentLoader {

    private final ObjectMapper mapper = new JsonMapper();

    public Race loadRace(Path jsonRace) throws IOException {
        return mapper.readValue(jsonRace.toFile(), Race.class);
    }
}
