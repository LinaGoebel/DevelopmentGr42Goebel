package de.ait.javalessons.homework.homework04.controller;

import de.ait.javalessons.homework.homework04.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    private List<Movie> movieList = new ArrayList<>();

    public MovieController() {
        movieList.addAll(
                List.of(
                        new Movie(1, "Brother", "Drama", 1997),
                        new Movie(2, "The Return", "Drama", 2003),
                        new Movie(3, "Leviathan", "Drama", 2014),
                        new Movie(4, "Stalker", "Sci-Fi", 1979)
                ));
    }

    @GetMapping
    Iterable<Movie> getMovies() {
        return movieList;
    }

    @GetMapping("/{id}")
    Optional<Movie> getMovieById(@PathVariable int id) {
        for (Movie movie : movieList) {
            if (movie.getId() == id) {
                return Optional.of(movie);
            }
        }
        return Optional.empty();
    }

    @PostMapping
    Movie postMovie(@RequestBody Movie movie) {
        movieList.add(movie);
        return movie;
    }

    @DeleteMapping("/{id}")
    void deleteMovie(@PathVariable int id) {
        movieList.removeIf(movie -> movie.getId() == id);
    }
}
