package com.jorji;

import com.jorji.content.CharacterClass;
import com.jorji.content.Item;
import com.jorji.content.Race;
import com.jorji.loader.ContentLoader;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

@Log4j
public class ContentRegistry {
    private final Map<String, Race> races = new HashMap<>();
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, CharacterClass> classes = new HashMap<>();

    public void loadAll(ContentLoader loader, Path rootDirectory) throws IOException {
        log.info("Loading races, classes, and items from: " + rootDirectory);
        loadDirectory(rootDirectory.resolve("races"),
                path -> register(races, loader.loadRace(path), Race::id));

        loadDirectory(rootDirectory.resolve("classes"),
                path -> register(classes, loader.loadClass(path), CharacterClass::id));

        loadDirectory(rootDirectory.resolve("items"),
                path -> register(items, loader.loadItem(path), Item::getId));
    }

    private void loadDirectory(Path directory, PathHandler handler) throws IOException {
        if (!Files.isDirectory(directory)) {
            log.error("Directory does not exist: " + directory);
            return;
        }

        try (Stream<Path> stream = Files.walk(directory)) {
            stream.filter(Files::isRegularFile)
                    .forEach(handler::handle);
        }
    }

    private <T> void register(Map<String, T> map, T value, Function<T, String> idExtractor) {
        map.put(idExtractor.apply(value), value);
    }

    public Race getRace(String id) {
        return require(races, id, "race");
    }

    public CharacterClass getCharacterClass(String id) {
        return require(classes, id, "class");
    }

    public Item getItem(String id) {
        return require(items, id, "item");
    }

    private <T> T require(Map<String, T> map, String id, String kind) {
        T value = map.get(id);
        if (value == null) {
            throw new java.util.NoSuchElementException("Unknown " + kind + " id: " + id);
        }
        return value;
    }
}
