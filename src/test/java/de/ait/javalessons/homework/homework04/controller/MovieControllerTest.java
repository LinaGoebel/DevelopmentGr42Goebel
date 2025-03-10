package de.ait.javalessons.homework.homework04.controller;

import de.ait.javalessons.homework.homework04.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieControllerTest {

    private MovieController movieController;

    @BeforeEach
    void setUp() {
        movieController = new MovieController();
    }

    @Test
    void testGetMovieReturnsMovie() {
        resultMovie();

        assertEquals(4, resultMovie().size());
        assertEquals("Brother", resultMovie().get(0).getTitle());
    }

    @Test
    void testGetMovieByIdReturnsMovie() {
        Optional<Movie> result = movieController.getMovieById(1);
        assertTrue(result.isPresent());
        assertEquals("Brother", result.get().getTitle());
    }

    @Test
    void testGetMovieByIdReturnsNotFound() {
        Optional<Movie> result = movieController.getMovieById(10);
        assertTrue(result.isEmpty());
    }

    @Test
    void testPostMovieAddsNewMovie() {
        Movie movieToAdd = new Movie(5, "Brigade", "Crime", 2002);
        movieController.postMovie(movieToAdd);
        assertEquals("Brigade", movieController.getMovieById(5).get().getTitle());
        assertEquals(5, movieController.getMovieById(5).get().getId());

        resultMovie();

        assertEquals(5, resultMovie().size());
    }

    @Test
    void testDeleteMovieRemovesMovie() {
        resultMovie();
        assertEquals(4, resultMovie().size());

        movieController.deleteMovie(1);
        List<Movie> updateMovie = resultMovie();
        assertEquals(3, updateMovie.size());

        Optional<Movie> deletedMovie = movieController.getMovieById(1);
        assertTrue(deletedMovie.isEmpty());
    }

    private List<Movie> resultMovie() {
        Iterable<Movie> resultMoviesIterable = movieController.getMovies();
        List<Movie> resultMovies = new ArrayList<>();
        resultMoviesIterable.forEach(resultMovies::add);

        return resultMovies;
    }


}
