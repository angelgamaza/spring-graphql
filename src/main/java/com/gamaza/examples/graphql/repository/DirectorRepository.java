package com.gamaza.examples.graphql.repository;

import com.gamaza.examples.graphql.entity.Director;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.graphql.constant.EntityConstants.DIRECTOR_ENTITY_GRAPH;

@Repository
@NonNullApi
public interface DirectorRepository extends JpaRepository<Director, UUID> {

    /**
     * Find a Director by its id
     *
     * @param id The ID of the Director
     * @return An Optional of the Director found
     */
    @Override
    @EntityGraph(value = DIRECTOR_ENTITY_GRAPH)
    Optional<Director> findById(UUID id);

}
