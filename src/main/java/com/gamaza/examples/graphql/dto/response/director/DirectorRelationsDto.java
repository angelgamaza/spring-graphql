package com.gamaza.examples.graphql.dto.response.director;

import com.gamaza.examples.graphql.dto.response.film.FilmDto;

import java.util.List;
import java.util.UUID;

/**
 * Record for Director with Relations
 */
public record DirectorRelationsDto(UUID id, String firstName, String lastName, List<FilmDto> films) {
}
