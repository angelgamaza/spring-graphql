package com.gamaza.examples.springgraphql.service.impl;

import com.gamaza.examples.springgraphql.dto.request.FilmPostDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmDto;
import com.gamaza.examples.springgraphql.dto.response.film.FilmRelationsDto;
import com.gamaza.examples.springgraphql.entity.Film;
import com.gamaza.examples.springgraphql.exception.AlreadyExistsException;
import com.gamaza.examples.springgraphql.exception.GenericRuntimeException;
import com.gamaza.examples.springgraphql.exception.NotFoundException;
import com.gamaza.examples.springgraphql.mapper.FilmMapper;
import com.gamaza.examples.springgraphql.repository.FilmRepository;
import com.gamaza.examples.springgraphql.service.FilmService;
import com.gamaza.examples.springgraphql.util.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.springgraphql.constant.EntityConstants.FILM_OBJECT_NAME;

@Service
public class FilmServiceImpl implements FilmService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    // Injected variables
    private final FilmRepository repository;
    private final FilmMapper mapper;

    /**
     * Constructor injection
     */
    public FilmServiceImpl(FilmRepository repository, FilmMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public FilmDto save(FilmPostDto postData) {
        // Result variable
        FilmDto result;
        try {
            // 1. Map the post object to an entity
            Film mappedEntity = mapper.asEntity(postData);
            // 2. Save in database
            Film savedEntity = repository.save(mappedEntity);
            // 3. Map to a DTO
            result = mapper.asDto(savedEntity);

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            // Error messages
            String errorMessage = ExceptionUtils.getCauseOrDefaultMessage(e);
            // Logs
            logger.error(errorMessage);
            // Throw a custom exception
            throw new AlreadyExistsException(FILM_OBJECT_NAME, String.format("name=%s", postData.name()));
        } catch (Exception e) {
            // Error messages
            String errorMessage = ExceptionUtils.getCauseOrDefaultMessage(e);
            // Logs
            logger.error(errorMessage);
            // Throw a custom exception
            throw new GenericRuntimeException(errorMessage);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public FilmRelationsDto findById(UUID id) {
        Optional<Film> retrievedEntity = repository.findById(id);
        if (retrievedEntity.isEmpty()) {
            throw new NotFoundException(FILM_OBJECT_NAME, String.format("id=%s", id));
        }
        return mapper.asRelationsDto(
                retrievedEntity.get()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public FilmRelationsDto findByName(String name) {
        Optional<Film> retrievedEntity = repository.findByName(name);
        if (retrievedEntity.isEmpty()) {
            throw new NotFoundException(FILM_OBJECT_NAME, String.format("name=%s", name));
        }
        return mapper.asRelationsDto(
                retrievedEntity.get()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDto> findAllByCountry(String country) {
        return repository
                .findAllByCountry(country)
                .stream()
                .map(mapper::asDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilmDto> findAllByDirectorId(UUID directorId) {
        return repository
                .findAllByDirectorId(directorId)
                .stream()
                .map(mapper::asDto)
                .toList();
    }

}
