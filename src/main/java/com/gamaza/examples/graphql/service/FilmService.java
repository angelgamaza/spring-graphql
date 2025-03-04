package com.gamaza.examples.graphql.service;

import com.gamaza.examples.graphql.dto.request.FilmPostDto;
import com.gamaza.examples.graphql.dto.response.film.FilmDto;
import com.gamaza.examples.graphql.dto.response.film.FilmRelationsDto;

import java.util.List;
import java.util.UUID;

/**
 * Service for Films
 */
public interface FilmService {

    /**
     * Save a Film
     *
     * @param postData The Film data to save
     * @return The saved Film
     */
    FilmDto save(FilmPostDto postData);

    /**
     * Find a Film by its ID
     *
     * @param id The ID of The Film
     * @return The found Film
     */
    FilmRelationsDto findById(UUID id);

    /**
     * Find a Film by its name
     *
     * @param name The name of The Film
     * @return The found Film
     */
    FilmRelationsDto findByName(String name);

    /**
     * Find all Films
     *
     * @return The found Films
     */
    List<FilmDto> findAll();

    /**
     * Find all Films by Country
     *
     * @param country The country name
     * @return The found Films
     */
    List<FilmDto> findAllByCountry(String country);

    /**
     * Find all Films by Director ID
     *
     * @param directorId The ID of The Director
     * @return The found Films
     */
    List<FilmDto> findAllByDirectorId(UUID directorId);

}
