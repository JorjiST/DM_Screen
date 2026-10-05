package com.jorji;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.dto.HeroMapper;
import com.jorji.hero.Hero;
import com.jorji.hero.HeroDTO;
import lombok.extern.log4j.Log4j;
import java.io.IOException;
import java.nio.file.Path;

@Log4j
public class HeroRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HeroMapper heroMapper = new HeroMapper();
    private final Path defaultPath = Path.of("heroes");

    public void save(Hero hero, Path file) {
        try{
            mapper.writeValue(file.toFile(), heroMapper.toDTO(hero));
        } catch (IOException e) {
            log.error("Не удалось сохранить персонажа", e);
        }
    }

    public Hero read(Path file, ContentRegistry contentRegistry){
        Hero hero = null;
        try {
            HeroDTO dto = mapper.readValue(file.toFile(), HeroDTO.class);
            hero = heroMapper.toEntity(dto, contentRegistry);
        } catch (IOException e) {
            log.error("Не удалось загрузить персонажа", e);
        }
        return hero;
    }
}