# Spring Boot GraphQL - Basic Example

This project provides a basic example about how to use GraphQL with Spring Boot 3.4.3.

The service exposes the GraphQL service with mutations and queries defined at **schema.graphqls** file.

All the application is containerized with Docker and Docker Compose, running the application at the 8081 port and a PostgreSQL database at 5432 port.

## Prerequisites

To run this application, you must have the following:

- Docker and Docker Compose.
- Basic knowledge of Docker.
- A working internet connection to pull Docker images.

Optional (for manually runs):

- Java 21.
- Maven 3.9.3.

## How to Run

1. Start the Containers:

```bash
docker-compose up --build
```

This command will build the Spring Boot application image (using the provided Dockerfile) and start both the PostgreSQL database and the Spring Boot application.

2. Verify the Setup:

- **PostgreSQL database**: The instance will be running and listening on port 5432.
- **Spring Boot Application**: Access the application at <http://localhost:8081>.

## Stopping the Services

To stop the containers, run:

```bash
docker-compose down
```

## GraphQL Reference

In order to try the GraphQL application, you can access to <http://localhost:8081/graphiql?path=/graphql>, that provides a UI to test the queries and mutations created.

### Queries

The service provides queries in order to retrieve:

- **Directors**:
    - `findDirectorById(id: ID!): DirectorRelations!`: Find a Director in database by its ID.
    - `findAllDirectors: [Director]!`: Find all Directors in database.
- **Films**:
    - `findFilmById(id: ID!): FilmRelations!`: Find a Film in database by its ID.
    - `findFilmByName(name: String!): FilmRelations!`: Find a Film in database by its name.
    - `findAllFilms: [Film]!`: Find all Films in database.
    - `findAllFilmsByCountry(country: String!): [Film]!`: Find all Films in database by country.
    - `findAllFilmsByDirectorId(directorId: ID!): [Film]!`: Find all Films in database by Director.

#### Usage example

**Query** `findAllFilms: [Film]!`:

```graphql
query {
    findAllFilms {
        name,
        country
    }
}
```

- Response:

```json
{
  "data": {
    "findAllFilms": [
      {
        "name": "Jurassic Park",
        "country": "USA"
      },
      {
        "name": "Jaws",
        "country": "USA"
      },
      {
        "name": "Indiana Jones",
        "country": "USA"
      },
      {
        "name": "E.T.",
        "country": "USA"
      },
      {
        "name": "Star Wars",
        "country": "USA"
      },
      {
        "name": "The Empire Strikes Back",
        "country": "USA"
      },
      {
        "name": "Return of the Jedi",
        "country": "USA"
      },
      {
        "name": "The Terminator",
        "country": "USA"
      },
      {
        "name": "Aliens",
        "country": "USA"
      },
      {
        "name": "Titanic",
        "country": "USA"
      },
      {
        "name": "Pulp Fiction",
        "country": "USA"
      },
      {
        "name": "Kill Bill",
        "country": "USA"
      },
      {
        "name": "Django Unchained",
        "country": "USA"
      },
      {
        "name": "Inception",
        "country": "USA"
      },
      {
        "name": "Interstellar",
        "country": "USA"
      }
    ]
  }
}
```

**Query** `findDirectorById(id: ID!): DirectorRelations!`:

```graphql
query {
    findDirectorById(id: "") {
        firstName,
        lastName
    }
}
```

- Response:

```json
{
  "data": {
    "findDirectorById": {
      "firstName": "Steven",
      "lastName": "Spielberg"
    }
  }
}
```

### Mutations

The service provides mutations in order to create:

- **Directors**:
    - `saveDirector(input: DirectorInput!) : Director!`: Create a Director in database.
- **Films**:
    - `saveFilm(input: FilmInput!) : Film!`: Create a Film in database.

#### Usage example

**Mutation** `saveDirector(input: DirectorInput!) : Director!`:

```graphql
mutation {
    saveDirector(input: {firstName: "Test", lastName: "Test"}) {
        firstName
        lastName
    }
}
```

- Response:

```json
{
  "data": {
    "saveDirector": {
      "firstName": "Test",
      "lastName": "Test"
    }
  }
}
```

## Author

Ángel Gamaza - <angel.gamaza@gmail.com>
