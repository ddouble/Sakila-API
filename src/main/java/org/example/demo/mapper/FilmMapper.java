package org.example.demo.mapper;

import org.example.demo.dto.FilmDto;
import org.example.demo.dto.FilmFullDto;
import org.example.demo.dto.FilmInputDto;
import org.example.demo.model.Film;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {InventoryMapper.class, ActorMapper.class})
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mapping(source = "languageId", target = "language.id")
    @Mapping(source = "originalLanguageId", target = "originalLanguage.id")
    Film toEntity(FilmInputDto filmDto);

    FilmDto toDto(Film film);

    FilmFullDto toFullDto(Film film);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Film partialUpdate(FilmDto filmDto, @MappingTarget Film film);
}