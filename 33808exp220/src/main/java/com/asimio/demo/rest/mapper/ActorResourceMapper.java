package com.asimio.demo.rest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.asimio.demo.domain.FilmActor;
import com.asimio.demo.rest.model.Actor;

@Mapper
public interface ActorResourceMapper {

    ActorResourceMapper INSTANCE = Mappers.getMapper(ActorResourceMapper.class);

    @Mappings({
        @Mapping(target = "first", source = "actor.firstName"),
        @Mapping(target = "last", source = "actor.lastName")
    })
    Actor map(FilmActor actor);
}