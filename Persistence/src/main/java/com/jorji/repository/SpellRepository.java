package com.jorji.repository;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.HasID;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor 
public class SpellRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/spells";

    @Override
    public <Spell extends HasID> boolean save(Spell spell) {
        try{
            Path path = Path.of(defaultPath, spell.getId() + ".json");
            objectMapper.writeValue(path.toFile(), spell);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
