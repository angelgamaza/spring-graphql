package com.gamaza.examples.springgraphql.dto.response.director;

import com.gamaza.examples.springgraphql.dto.response.film.FilmDto;

import java.util.List;
import java.util.UUID;

/**
 * Record for Director with Relations
 */
public record DirectorRelationsDto(UUID id, String firstName, String lastName, List<FilmDto> films) {
}
