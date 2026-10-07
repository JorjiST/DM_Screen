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
public class CharacterClassRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/classes";

    @Override
    public <CharacterClass extends HasID> boolean save(CharacterClass characterClass) {
        try {
            Path path = Path.of(defaultPath + characterClass.getId() + ".json");
            objectMapper.writeValue(path.toFile(), characterClass);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());    
        }
    }
}
