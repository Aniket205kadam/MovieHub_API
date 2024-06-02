package dev.aniket.MovieHub_API.controller;

import dev.aniket.MovieHub_API.model.Review;
import dev.aniket.MovieHub_API.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping("/{movieId}")
    @ResponseStatus(HttpStatus.OK)
    public void createReview(@RequestBody Review review, @PathVariable Long movieId) {
        service.createReview(review, movieId);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        return new ResponseEntity<Review>(service.updateReview(reviewId, review), HttpStatus.OK);
    }

    @DeleteMapping("/{reviewId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable Long reviewId) {
        service.deleteReviewById(reviewId);
    }

    //get all the review according to the movie
    @GetMapping("movies/{movieId}")
    public List<Review> getReviewByMovie(@PathVariable Long movieId) {
        return service.findReviewsByMovieId(movieId);
    }
}
