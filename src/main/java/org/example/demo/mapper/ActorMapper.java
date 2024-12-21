package org.example.demo.mapper;

import org.example.demo.dto.ActorDto;
import org.example.demo.model.Actor;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ActorMapper {
    Actor toEntity(ActorDto actorDto);

    default String getFullName(Actor actor) {
        return actor.getFirstName() + " " + actor.getLastName();
    }

    @Mapping(target = "fullName", expression = "java(getFullName(actor))")
    ActorDto toDto(Actor actor);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Actor partialUpdate(ActorDto actorDto, @MappingTarget Actor actor);
}