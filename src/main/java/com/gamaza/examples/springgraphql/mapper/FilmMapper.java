package com.gamaza.examples.springgraphql.mapper;

import com.gamaza.examples.springgraphql.dto.request.FilmPostDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmRelationsDto;
import com.gamaza.examples.springgraphql.entity.Director;
import com.gamaza.examples.springgraphql.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Film
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface FilmMapper {

    /**
     * Entity mappings
     */
    @Mapping(source = "directorId", target = "director", qualifiedByName = "directorMapping")
    Film asEntity(FilmPostDto source);

    /**
     * DTO Mappings
     */
    FilmDto asDto(Film source);

    FilmRelationsDto asRelationsDto(Film source);

    /*
     * Custom Mappings
     */

    @Named("directorMapping")
    default Director directorMapping(UUID directorId) {
        return new Director(directorId);
    }

}
