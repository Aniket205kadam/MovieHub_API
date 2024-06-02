package dev.aniket.MovieHub_API.service;

import dev.aniket.MovieHub_API.exception.ReviewNotFoundException;
import dev.aniket.MovieHub_API.exception.ReviewsAreNotExistException;
import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.model.Review;
import dev.aniket.MovieHub_API.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final MovieService movieService;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(MovieService movieService, ReviewRepository reviewRepository) {
        this.movieService = movieService;
        this.reviewRepository = reviewRepository;
    }
    public void createReview(Review review, Long movieId) {
        //if the movie is not present then throw exception
        Movie movie = movieService.singleMovie(movieId);

        review.setMovie(movie);
        review.setTimestamp(LocalDateTime.now());

        //save review
        reviewRepository.save(review);

        //get existing review for the movies
        List<Review> existingReviews = movie.getReviewIds();
        //if the movie is not present in the db
        if(existingReviews.isEmpty()) {
            existingReviews = new ArrayList<>();
        }
        //new review is added
        existingReviews.add(review);
        //update the movie by the name review
        movieService.updateMovieReviews(movieId, existingReviews);
    }

    public Review updateReview(Long reviewId, Review review) {
        //first if the reviewId is present on the db
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new ReviewNotFoundException("Are you trying to find the review by its reviewId? It appears the review was not found.");
        }
        review.setReviewId(reviewId);
        return reviewRepository.save(review);
    }

    public void deleteReviewById(Long reviewId) {
        if (reviewRepository.findById(reviewId).isEmpty()) {
            throw new ReviewNotFoundException("Are you trying to find the review by its reviewId? It appears the review was not found.");
        }
        reviewRepository.deleteById(reviewId);
    }

    public List<Review> findReviewsByMovieId(Long movieId) {
        List<Review> reviews = reviewRepository.findByMovie(movieService.singleMovie(movieId));

        //check the review are present or not
        if (reviews == null) {
            throw new ReviewsAreNotExistException("Oops!, This movie are not review");
        }
        //return the reviews
        return reviews;
    }
}
