package com.jorji;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jorji.dto.HeroMapper;
import com.jorji.hero.Hero;

import java.io.IOException;
import java.nio.file.Path;

public class HeroRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private final HeroMapper heroMapper = new HeroMapper();
    private final Path defaultPath = Path.of("heroes");

    public void save(Hero hero, Path file) throws IOException {
        mapper.writeValue(file.toFile(), heroMapper.toDTO(hero));
    }
}