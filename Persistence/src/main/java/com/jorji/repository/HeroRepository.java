package com.jorji.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.ContentRegistry;
import com.jorji.HeroMapper;
import com.jorji.hero.Hero;
import com.jorji.hero.HeroDTO;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import java.io.IOException;
import java.nio.file.Path;

@Log4j
@Setter
@RequiredArgsConstructor 
public class HeroRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HeroMapper heroMapper = new HeroMapper();
    private String defaultPath = "data/heroes";

    public boolean save(Hero hero) {
        try{
            Path path = Path.of(defaultPath, hero.getName() + ".json");
            mapper.writeValue(path.toFile(), heroMapper.toDTO(hero));
            return true;
        } catch (IOException e) {
            log.error("Не удалось сохранить персонажа", e);
            return false;
        }
    }

    public Hero read(Path file, ContentRegistry contentRegistry){
        Hero hero = null;
        Path path = Path.of(defaultPath + "/" + file);
        try {
            HeroDTO dto = mapper.readValue(path.toFile(), HeroDTO.class);
            hero = heroMapper.toEntity(dto, contentRegistry);
        } catch (IOException e) {
            log.error("Не удалось загрузить персонажа", e);
        }
        return hero;
    }
}