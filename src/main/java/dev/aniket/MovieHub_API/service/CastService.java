package dev.aniket.MovieHub_API.service;

import dev.aniket.MovieHub_API.exception.NotFoundException;
import dev.aniket.MovieHub_API.model.Cast;
import dev.aniket.MovieHub_API.model.CastDto;
import dev.aniket.MovieHub_API.model.Movie;
import dev.aniket.MovieHub_API.model.MovieDto;
import dev.aniket.MovieHub_API.repository.CastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CastService {
    private CastRepository castRepository;

    @Autowired
    public CastService(CastRepository castRepository) {
        this.castRepository = castRepository;
    }
    public Cast getCastById(Long castId) {
        Optional<Cast> cast = castRepository.findById(castId);

        if (cast.isEmpty()) {
            throw new NotFoundException("The given id is not match any cast..!");
        }
        return cast.get();
    }

    public CastDto getCastDtoById(Long castId) {
        Optional<Cast> cast = castRepository.findById(castId);

        if (cast.isEmpty()) {
            throw new NotFoundException("The given id does not match any cast member.");
        }

        CastDto castDto = new CastDto();
        castDto.setCastId(cast.get().getCastId());
        castDto.setCastName(cast.get().getCastName());
        castDto.setAvatar(cast.get().getAvatar());
        castDto.setCastAge(cast.get().getCastAge());
        castDto.setCastHeight(cast.get().getCastHeight());
        castDto.setCastWeight(cast.get().getCastWeight());
        castDto.setBorn(cast.get().getBorn());
        castDto.setInstagram(cast.get().getInstagram());
        castDto.setTwitter(cast.get().getTwitter());
        castDto.setFacebook(cast.get().getFacebook());

        //list of movies
        List<Movie> existingMovies = cast.get().getMovies();

        List<MovieDto> movieDtos = new ArrayList<>();

        for (Movie movie : existingMovies) {
            movieDtos.add(new MovieDto(movie.getMovieId(), movie.getTitle(), movie.getPoster()));
        }
        castDto.setMovies(movieDtos);

        return castDto;
    }

    //add the cast
    public Cast addCast(Cast cast) {
        return castRepository.save(cast);
    }

    //update the cast
    public Cast updateCast(Cast cast, Long castId) {
        //check the castId is present or not
        getCastById(castId);

        cast.setCastId(castId);
        //update cast
        return castRepository.save(cast);
    }

    //delete cast
    public void deleteCast(Long castId) {
        //check castId is present or not
        getCastById(castId);

        castRepository.deleteById(castId);
    }

    public List<Cast> getAllCast() {
        return castRepository.findAll();
    }

    public CastDto getCastDtoByName(String castName) {
        Optional<Cast> cast = castRepository.findByCastName(castName);

        if (cast.isEmpty()) {
            throw new NotFoundException("The given name does not match any cast member");
        }

        CastDto castDto = new CastDto();
        castDto.setCastId(cast.get().getCastId());
        castDto.setCastName(cast.get().getCastName());
        castDto.setAvatar(cast.get().getAvatar());
        castDto.setCastAge(cast.get().getCastAge());
        castDto.setCastHeight(cast.get().getCastHeight());
        castDto.setCastWeight(cast.get().getCastWeight());
        castDto.setBorn(cast.get().getBorn());
        castDto.setInstagram(cast.get().getInstagram());
        castDto.setTwitter(cast.get().getTwitter());
        castDto.setFacebook(cast.get().getFacebook());

        //list of movies
        List<Movie> existingMovies = cast.get().getMovies();

        List<MovieDto> movieDtos = new ArrayList<>();

        for (Movie movie : existingMovies) {
            movieDtos.add(new MovieDto(movie.getMovieId(), movie.getTitle(), movie.getPoster()));
        }
        castDto.setMovies(movieDtos);

        return castDto;
    }
}
