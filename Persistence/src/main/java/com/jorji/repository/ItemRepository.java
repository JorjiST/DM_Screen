package com.jorji.repository;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.HasID;
import com.jorji.content.Item;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Setter 
@RequiredArgsConstructor 
public class ItemRepository implements ContentRepository {
    private final ObjectMapper objectMapper;
    private String defaultPath = "data/items";

    @Override 
    public <Item extends HasID> boolean save(Item item) {
        try {
            Path path = Path.of(defaultPath, item.getId() + ".json");
            objectMapper.writeValue(path.toFile(), item);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
