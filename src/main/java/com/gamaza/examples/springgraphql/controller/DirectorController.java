package com.gamaza.examples.springgraphql.controller;

import com.gamaza.examples.springgraphql.dto.request.DirectorPostDto;
import com.gamaza.examples.springgraphql.dto.response.director.DirectorDto;
import com.gamaza.examples.springgraphql.dto.response.director.DirectorRelationsDto;
import com.gamaza.examples.springgraphql.service.DirectorService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class DirectorController {

    // Injection variables
    private final DirectorService service;

    /**
     * Constructor injection
     */
    public DirectorController(DirectorService service) {
        this.service = service;
    }

    @MutationMapping(value = "saveDirector")
    public DirectorDto save(@Argument DirectorPostDto input) {
        return service.save(input);
    }

    @QueryMapping(value = "findDirectorById")
    public DirectorRelationsDto findById(@Argument UUID id) {
        return service.findById(id);
    }

    @QueryMapping(value = "findAllDirectors")
    public List<DirectorDto> findAll() {
        return service.findAll();
    }

}
