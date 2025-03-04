package com.gamaza.examples.graphql.dto.response.film;

import java.util.UUID;

/**
 * Record for Film
 */
public record FilmDto(UUID id, String name, String country) {
}
