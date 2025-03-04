package com.gamaza.examples.graphql.dto.request;

import java.util.UUID;

/**
 * Record POST for Film
 */
public record FilmPostDto(String name, String country, UUID directorId) {
}
