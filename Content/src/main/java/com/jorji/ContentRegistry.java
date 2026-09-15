package com.jorji;

import com.jorji.content.CharacterClass;
import com.jorji.content.Item;
import com.jorji.content.Race;
import com.jorji.loader.ContentLoader;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ContentRegistry {
    private final Map<String, Race> races = new HashMap<>();
    private final Map<String, Item> items = new HashMap<>();
    private final Map<String, CharacterClass> classes = new HashMap();

    public void loadAll(ContentLoader contentLoader, Path directory){

    }

    public Race getRace(String id) {
        Race race = races.get(id);
        if (race == null) {
            throw new NoSuchElementException("Unknown race id: " + id);
        }
        return race;
    }

    public CharacterClass getClass(String id) {
        CharacterClass characterClass = classes.get(id);
        if (characterClass == null) {
            throw new NoSuchElementException("Unknown class id: " + id);
        }
        return characterClass;
    }

    public Item getItem(String id) {
        Item item = items.get(id);
        if (item == null) {
            throw new NoSuchElementException("Unknown item id: " + id);
        }
        return item;
    }
}
