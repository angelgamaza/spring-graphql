package com.gamaza.examples.springgraphql.mapper;

import com.gamaza.examples.springgraphql.dto.request.DirectorPostDto;
import com.gamaza.examples.springgraphql.dto.response.director.DirectorDto;
import com.gamaza.examples.springgraphql.dto.response.director.DirectorRelationsDto;
import com.gamaza.examples.springgraphql.entity.Director;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

/**
 * Mapper for Director
 */
@Mapper(componentModel = SPRING, unmappedSourcePolicy = IGNORE, unmappedTargetPolicy = IGNORE, typeConversionPolicy = IGNORE)
public interface DirectorMapper {

    /**
     * Entity mappings
     */
    Director asEntity(DirectorPostDto source);

    /**
     * DTO Mappings
     */
    DirectorDto asDto(Director source);

    DirectorRelationsDto asRelationsDto(Director source);

}
