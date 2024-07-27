package dev.aniket.MovieHub_API.controller;

import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.service.MovieService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@NoArgsConstructor
@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        return new ResponseEntity<List<Movie>>(movieService.allMovies(), HttpStatus.OK);
    }

    @GetMapping("/id/{movieId}")
    public ResponseEntity<Movie> getSingleMovieById(@PathVariable Long movieId) {
        return new ResponseEntity<Movie>(movieService.singleMovie(movieId), HttpStatus.OK);
    }

    @GetMapping("/{movieTitle}")
    public ResponseEntity<Movie> getSingleMovieByTitle(@PathVariable("movieTitle") String title) {
        return new ResponseEntity<Movie>(movieService.singleMovie(title), HttpStatus.OK);
    }

    //search movie
    @GetMapping("/search/{searchKey}")
    public ResponseEntity<List<Movie>> getSearchResult(@PathVariable String searchKey) {
        return new ResponseEntity<List<Movie>>(movieService.getSearchResult(searchKey), HttpStatus.OK);
    }

}

//cmd - > curl -i http://localhost:8080/api/v1/movies