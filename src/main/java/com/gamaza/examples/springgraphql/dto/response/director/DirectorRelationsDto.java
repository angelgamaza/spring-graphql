package com.gamaza.examples.springgraphql.dto.response.director;

import com.gamaza.examples.springgraphql.dto.response.film.FilmDto;

import java.util.Set;
import java.util.UUID;

/**
 * Record for Director with Relations
 */
public record DirectorRelationsDto(UUID id, String firstName, String lastName, Set<FilmDto> films) {
}
