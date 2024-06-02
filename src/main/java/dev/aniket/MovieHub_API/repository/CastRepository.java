package dev.aniket.MovieHub_API.repository;

import dev.aniket.MovieHub_API.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CastRepository extends JpaRepository<Cast, Long> {
    Optional<Cast> findByCastName(String castName);
}
