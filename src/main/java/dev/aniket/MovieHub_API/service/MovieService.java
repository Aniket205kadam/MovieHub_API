package dev.aniket.MovieHub_API.service;

import dev.aniket.MovieHub_API.exception.NotFoundException;
import dev.aniket.MovieHub_API.model.Cast;
import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.model.Review;
import dev.aniket.MovieHub_API.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;
    private CastService castService;

    @Autowired
    public MovieService(MovieRepository movieRepository, CastService castService) {
        this.movieRepository = movieRepository;
        this.castService = castService;
    }
    public List<Movie> allMovies() {
        return movieRepository.findAll();
    }

    public Movie singleMovie(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);

        if (movie.isEmpty()) {
            throw new NotFoundException("Are you trying to find the movie by its movieId? It appears the movie was not found.");
        }

        return movie.get();
    }

    public Movie singleMovie(String title) {
        Optional<Movie> movie = movieRepository.findByTitle(title);

        if (movie.isEmpty()) {
            throw new NotFoundException("Are you trying to find the movie by its title? It appears the movie was not found.");
        }

        return movie.get();
    }

    //working
    public Movie updateMovieReviews(Long movieId, List<Review> reviews) {
        Movie movie = singleMovie(movieId);
        movie.setReviewIds(reviews);

        //update the
        return movieRepository.save(movie);
    }

    //stored or update the movie
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    //delete the movie
    public void deleteMovie(Long movieId) {
        //if the movie id is not found then throws the exception
        singleMovie(movieId);
        movieRepository.deleteById(movieId);
    }

    //now update the movie with new cast
    public void addMovieCast(Long movieId, Long castId) {
        Cast cast = castService.getCastById(castId);
        Movie movie = singleMovie(movieId);

        List<Cast> movieCasts = movie.getMovieCasts();

        if (movieCasts == null) {
            movieCasts = new ArrayList<>();
        }
        //add new cast
        movieCasts.add(cast);

        movie.setMovieCasts(movieCasts);
        movie.setMovieId(movieId);

        //update movie
        movieRepository.save(movie);
    }

    //search
    public List<Movie> getSearchResult(String searchKey) {
        List<Movie> movies = movieRepository.findByTitleContainingOrReleaseDateContainingOrDirectedByContainingOrWrittenByContainingOrTaglineContainingOrLanguageContainingOrSynopsisContaining
                (
                    searchKey,
                    searchKey,
                    searchKey,
                    searchKey,
                    searchKey,
                    searchKey,
                    searchKey
            );

        if (movies.isEmpty()) {
            throw new NotFoundException("No matches were found for your search");
        }

        return movies;
    }

//    //Experiment purpose
//    public Object getMovieDemo(Long movieId) {
//        return movieRepository.findMovie(movieId);
//    }
}
