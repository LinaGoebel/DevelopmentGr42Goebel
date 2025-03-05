package de.ait.javalessons.homework.homework04.controller;

import de.ait.javalessons.homework.homework04.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIT {

    @Autowired
    private MovieController movieController;

    private static final String BASE_URL = "/movies";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetMovieReturnsMovie() {
        ResponseEntity<Movie[]> response = testRestTemplate.getForEntity(BASE_URL, Movie[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(4, response.getBody().length);
        assertEquals("Brother", response.getBody()[0].getTitle());
    }

    @Test
    void testGetMovieByIdReturnsMovie() {
        ResponseEntity<Movie> response = testRestTemplate.getForEntity(BASE_URL + "/1", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Brother", response.getBody().getTitle());
    }

    @Test
    void testGetMovieByIdReturnsNotFound() {
        ResponseEntity<Movie> response = testRestTemplate.getForEntity(BASE_URL + "/10", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testPostMovieAddsNewMovie() {
        Movie movieToAdd = new Movie(5, "Brigade", "Crime", 2002);
        ResponseEntity<Movie> response = testRestTemplate.postForEntity(BASE_URL, movieToAdd, Movie.class);
        movieController.postMovie(movieToAdd);
        assertEquals("Brigade", movieController.getMovieById(5).get().getTitle());
        assertEquals(5, movieController.getMovieById(5).get().getId());
    }

    @Test
    void testDeleteMovieRemovesMovie() {
        ResponseEntity<Movie> response = testRestTemplate.getForEntity(BASE_URL + "/1", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Brother", response.getBody().getTitle());
    }

}
