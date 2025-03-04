package com.gamaza.examples.springgraphql.dto.response.film;

import com.gamaza.examples.springgraphql.dto.response.director.DirectorDto;

import java.util.UUID;

/**
 * Record for Film with Relations
 */
public record FilmRelationsDto(UUID id, String name, String country, DirectorDto director) {
}
