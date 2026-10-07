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
public class FeatRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/feats";

    @Override
    public <Feat extends HasID> boolean save(Feat feat) {
        try {
            Path path = Path.of(defaultPath, feat.getId() + ".json");
            objectMapper.writeValue(path.toFile(), feat);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
