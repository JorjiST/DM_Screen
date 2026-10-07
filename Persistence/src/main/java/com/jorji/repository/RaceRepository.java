package com.jorji.repository;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.HasID;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Setter 
@RequiredArgsConstructor 
public class RaceRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/races";

    @Override
    public <Race extends HasID> boolean save(Race race) {
        try {
            Path path = Path.of(defaultPath, race.getId() + ".json");
            objectMapper.writeValue(path.toFile(), race);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}