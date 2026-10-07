package com.jorji.repository;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.HasID;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@RequiredArgsConstructor 
public class PeculiarityRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/peculiarities";

    @Override
    public <Peculiarity extends HasID> boolean save(Peculiarity peculiarity) {
        try{
            Path path = Path.of(defaultPath, peculiarity.getId() + ".json");
            objectMapper.writeValue(path.toFile(), peculiarity);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
