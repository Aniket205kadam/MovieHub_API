package dev.aniket.MovieHub_API.repository;

import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Review findByReviewBody(String reviewBody);
    List<Review> findByMovie(Movie movie);

}
