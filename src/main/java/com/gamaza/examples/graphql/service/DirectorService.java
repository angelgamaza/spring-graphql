package com.gamaza.examples.graphql.service;

import com.gamaza.examples.graphql.dto.request.DirectorPostDto;
import com.gamaza.examples.graphql.dto.response.director.DirectorDto;
import com.gamaza.examples.graphql.dto.response.director.DirectorRelationsDto;

import java.util.List;
import java.util.UUID;

/**
 * Service for Director
 */
public interface DirectorService {

    /**
     * Save a Director
     *
     * @param postData the Director data to save
     * @return The saved Director
     */
    DirectorDto save(DirectorPostDto postData);

    /**
     * Find a Director by its ID
     *
     * @param id the ID of the Director
     * @return The found Director
     */
    DirectorRelationsDto findById(UUID id);

    /**
     * Find all Directors
     *
     * @return The found Directors
     */
    List<DirectorDto> findAll();

}
