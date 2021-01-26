package com.asimio.demo.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.asimio.demo.domain.Film;
import com.asimio.demo.rest.model.FilmResource;

@Mapper(uses = ActorResourceMapper.class)
public interface FilmResourceMapper {

    FilmResourceMapper INSTANCE = Mappers.getMapper(FilmResourceMapper.class);

    @Mappings({
        @Mapping(target = "lang", expression = "java(film.getLanguage().getName().trim())"),
        @Mapping(target = "actors", source = "filmActors")
    })
    FilmResource map(Film film);

    List<FilmResource> map(List<Film> film);
}