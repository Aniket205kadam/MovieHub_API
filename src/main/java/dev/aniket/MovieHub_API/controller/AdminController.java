package dev.aniket.MovieHub_API.controller;

import dev.aniket.MovieHub_API.model.Cast;
import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.service.CastService;
import dev.aniket.MovieHub_API.service.MovieService;
import dev.aniket.MovieHub_API.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    private MovieService movieService;
    private CastService castService;
    private ReviewService reviewService;

    @Autowired
    public AdminController(MovieService movieService, CastService castService, ReviewService reviewService) {
        this.movieService = movieService;
        this.castService = castService;
        this.reviewService = reviewService;
    }
    @PostMapping("/{adminId}/movie")
    public ResponseEntity<Movie> addMovie(@PathVariable String adminId, @RequestBody Movie movie) {
        //when create the user that time check this admin is fake or original
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
    }

    @PutMapping("/{adminId}/movie/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String adminId, @PathVariable Long movieId, @RequestBody Movie movie) {
        //when create the user that time check this admin is fake or original
        movie.setMovieId(movieId);
        return new ResponseEntity<>(movieService.saveMovie(movie), HttpStatus.OK);
    }

    @DeleteMapping("/{adminId}/movie/{movieId}")
    public void deleteMovie(@PathVariable String adminId, @PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
    }

    //add the cast to the movie
    @GetMapping("/{adminId}/movie/{movieId}/cast/{castId}")
    @ResponseStatus(HttpStatus.OK)
    public void addMovieCast(@PathVariable String adminId, @PathVariable Long movieId, @PathVariable Long castId) {
        //check the admin id
        movieService.addMovieCast(movieId, castId);
    }

    //add cast
    @PostMapping("/{adminId}/cast")
    public ResponseEntity<Cast> addCast(@RequestBody Cast cast) {
        return new ResponseEntity<>(castService.addCast(cast), HttpStatus.OK);
    }

    //update cast
    @PutMapping("/{adminId}/cast{castId}")
    public ResponseEntity<Cast> updateCast(@RequestBody Cast cast, @PathVariable Long castId, @PathVariable String adminId) {
        return new ResponseEntity<>(castService.updateCast(cast, castId), HttpStatus.OK);
    }

    //delete cast
    @DeleteMapping("/{adminId}/cast/{castId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCast(@PathVariable Long castId, @PathVariable String adminId) {
        castService.deleteCast(castId);
    }

    //admin also delete the review if the review language is not good
    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReviewById(reviewId);
    }
}


//admin Id - > $1@a63hsx82hd