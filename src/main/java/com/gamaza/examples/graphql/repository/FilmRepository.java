package com.gamaza.examples.graphql.repository;

import com.gamaza.examples.graphql.entity.Film;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.graphql.constant.EntityConstants.FILM_ENTITY_GRAPH;

@Repository
@NonNullApi
public interface FilmRepository extends JpaRepository<Film, UUID> {

    /**
     * Find a Film by its id
     *
     * @param id The ID of the Film
     * @return An Optional of the Film found
     */
    @Override
    @EntityGraph(value = FILM_ENTITY_GRAPH)
    Optional<Film> findById(UUID id);

    /**
     * Find a Film by its name
     *
     * @param name The name of the Film to find
     * @return An Optional of the Film found
     */
    @EntityGraph(value = FILM_ENTITY_GRAPH)
    Optional<Film> findByName(String name);

    /**
     * Find all Films by its country
     *
     * @param country The country of the Film
     * @return A List of the Films found
     */
    List<Film> findAllByCountry(String country);

    /**
     * Find all Films by its director id
     *
     * @param directorId The ID of the Director
     * @return A List of the Films found
     */
    @SuppressWarnings("SpringDataRepositoryMethodParametersInspection")
    List<Film> findAllByDirectorId(UUID directorId);

}
