package com.gamaza.examples.graphql.service.impl;

import com.gamaza.examples.graphql.dto.request.DirectorPostDto;
import com.gamaza.examples.graphql.dto.response.director.DirectorDto;
import com.gamaza.examples.graphql.dto.response.director.DirectorRelationsDto;
import com.gamaza.examples.graphql.entity.Director;
import com.gamaza.examples.graphql.exception.AlreadyExistsException;
import com.gamaza.examples.graphql.exception.GenericRuntimeException;
import com.gamaza.examples.graphql.exception.NotFoundException;
import com.gamaza.examples.graphql.mapper.DirectorMapper;
import com.gamaza.examples.graphql.repository.DirectorRepository;
import com.gamaza.examples.graphql.service.DirectorService;
import com.gamaza.examples.graphql.util.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.gamaza.examples.graphql.constant.EntityConstants.DIRECTOR_OBJECT_NAME;

@Service
public class DirectorServiceImpl implements DirectorService {

    // Logger
    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    // Injected variables
    private final DirectorRepository repository;
    private final DirectorMapper mapper;

    /**
     * Constructor injection
     */
    public DirectorServiceImpl(DirectorRepository repository, DirectorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public DirectorDto save(DirectorPostDto postData) {
        // Result variable
        DirectorDto result;
        try {
            // 1. Map the post object to an entity
            Director mappedEntity = mapper.asEntity(postData);
            // 2. Save in database
            Director savedEntity = repository.save(mappedEntity);
            // 3. Map to a DTO
            result = mapper.asDto(savedEntity);

        } catch (DataIntegrityViolationException | ConstraintViolationException e) {
            // Error messages
            String errorMessage = ExceptionUtils.getCauseOrDefaultMessage(e);
            // Logs
            logger.error(errorMessage);
            // Throw a custom exception
            throw new AlreadyExistsException(
                    DIRECTOR_OBJECT_NAME,
                    String.format("firstName=%s, lastName=%s", postData.firstName(), postData.lastName())
            );
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
    public DirectorRelationsDto findById(UUID id) {
        Optional<Director> retrievedEntity = repository.findById(id);
        if (retrievedEntity.isEmpty()) {
            throw new NotFoundException(DIRECTOR_OBJECT_NAME, String.format("id=%s", id));
        }
        return mapper.asRelationsDto(
                retrievedEntity.get()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<DirectorDto> findAll() {
        return repository
                .findAll()
                .stream()
                .map(mapper::asDto)
                .toList();
    }

}
