package com.gamaza.examples.graphql.dto.response.film;

import com.gamaza.examples.graphql.dto.response.director.DirectorDto;

import java.util.UUID;

/**
 * Record for Film with Relations
 */
public record FilmRelationsDto(UUID id, String name, String country, DirectorDto director) {
}
