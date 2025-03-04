package com.gamaza.examples.graphql.dto.response.director;

import java.util.UUID;

/**
 * Record for Director
 */
public record DirectorDto(UUID id, String firstName, String lastName) {
}
