package dev.aniket.MovieHub_API.repository;

import dev.aniket.MovieHub_API.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String title);

//    List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);
    List<Movie> findByTitleContainingOrReleaseDateContainingOrDirectedByContainingOrWrittenByContainingOrTaglineContainingOrLanguageContainingOrSynopsisContaining
        (
            String title,
            String releaseDate,
            String directedBy,
            String writtenBy,
            String tagline,
            String language,
            String Synopsis
        );

    //Experiment purpose
//    @Query("SELECT new dev.aniket.MovieHub_API.dto.ReviewDTO(r.reviewBody, m.backdrops, m.directedBy, m.genres, m.poster, m.releaseDate, m.runtime, m.tagline, m.title, m.trailerLink, m.writtenBy, m.synopsis) " +
//            "FROM Review r JOIN r.movie m WHERE r.movie.movieId = :movieId")
//    Object findMovie(Long movieId);
}
