package com.gamaza.examples.springgraphql.dto.request;

import java.util.UUID;

/**
 * Record POST for Film
 */
public record FilmPostDto(String name, String country, UUID directorId) {
}
