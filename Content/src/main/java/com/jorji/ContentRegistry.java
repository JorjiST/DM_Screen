package com.jorji;

import com.jorji.content.*;
import com.jorji.loader.ContentLoader;

import lombok.extern.log4j.Log4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Stream;

@Log4j
public class ContentRegistry {
    private final Map<String, Race> races = new HashMap<>();
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, CharacterClass> classes = new HashMap<>();
    private final Map<String, Spell> spells = new HashMap<>();
    private final ContentLoader loader = new ContentLoader();

    public void loadAll(Path rootDirectory) throws IOException {
        log.info("Loading races, classes, and items from: " + rootDirectory);
        loadDirectory(rootDirectory.resolve("races"),
                path -> register(races, loader.loadRace(path), Race::id));

        loadDirectory(rootDirectory.resolve("classes"),
                path -> register(classes, loader.loadClass(path), CharacterClass::id));

        loadDirectory(rootDirectory.resolve("items"),
                path -> register(items, loader.loadItem(path), Item::getId));

        loadDirectory(rootDirectory.resolve("spells"),
                path -> register(spells, loader.loadSpell(path), Spell::id));
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
        return require(races, id, "race", Race.class);
    }

    public CharacterClass getCharacterClass(String id) {
        return require(classes, id, "class", CharacterClass.class);
    }

    public Item getItem(String id) {
        return require(items, id, "item", Item.class);
    }

    public Spell getSpell(String id) {
        return require(spells, id, "spell", Spell.class);
    }

    private <T> T require(Map<String, T> map, String id, String kind, Class<T> type) {
        T value = map.get(id);
        if (value != null) return value;
        else {
            value = tryLoadContent(id, kind, type);
            if (value != null) {
                map.put(id, value);
            } else {
                throw new NoSuchElementException("Не удалось загрузить контент: " + id);
            }
        }
        return value;
    }

    private Path resolveResourcePath(String relativePath) throws URISyntaxException, FileNotFoundException {
        URL url = getClass().getResource("/" + relativePath);
        if (url == null) {
            throw new FileNotFoundException("Ресурс не найден: " + relativePath);
        }
        return Path.of(url.toURI());
    }

    private <T> T tryLoadContent(String id, String kind, Class<T> type) {
        final String json = ".json";
        Object o = null;
        try {
            o = switch (kind) {
                case "class" -> loader.loadClass(resolveResourcePath("classes/" + id + json));
                case "race" -> loader.loadRace(resolveResourcePath("races/" + id + json));
                case "item" -> loader.loadItem(resolveResourcePath("items/" + id + json));
                case "spell" -> loader.loadSpell(resolveResourcePath("spells/" + id + json));
                default -> throw new IllegalArgumentException("Неизвестный тип контента: " + kind);
            };
        } catch (FileNotFoundException | IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (URISyntaxException e) {
            log.error(e.getMessage() + "\nНекорректный путь к контенту");
        } catch (IOException e) {
            log.error(e.getMessage() + "\nОшибка чтения контента");
        }
        return type.cast(o);
    }
}
