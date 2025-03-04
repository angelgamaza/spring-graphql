package com.gamaza.examples.springgraphql.controller;

import com.gamaza.examples.springgraphql.dto.request.FilmPostDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmRelationsDto;
import com.gamaza.examples.springgraphql.service.FilmService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class FilmController {

    // Injection variables
    private final FilmService service;

    /**
     * Constructor injection
     */
    public FilmController(FilmService service) {
        this.service = service;
    }

    @MutationMapping(value = "saveFilm")
    public FilmDto save(@Argument FilmPostDto input) {
        return service.save(input);
    }

    @QueryMapping(value = "findFilmById")
    public FilmRelationsDto findById(@Argument UUID id) {
        return service.findById(id);
    }

    @QueryMapping(value = "findFilmByName")
    public FilmRelationsDto findByName(@Argument String name) {
        return service.findByName(name);
    }

    @QueryMapping(value = "findAllFilms")
    public List<FilmDto> findAll() {
        return service.findAll();
    }

    @QueryMapping(value = "findAllFilmsByCountry")
    public List<FilmDto> findAllByCountry(@Argument String country) {
        return service.findAllByCountry(country);
    }

    @QueryMapping(value = "findAllFilmsByDirectorId")
    public List<FilmDto> findAllByDirectorId(@Argument UUID directorId) {
        return service.findAllByDirectorId(directorId);
    }

}
