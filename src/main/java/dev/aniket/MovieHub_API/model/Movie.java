package dev.aniket.MovieHub_API.model;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_movie"
    )
    @SequenceGenerator(
            name = "seq_movie",
            sequenceName = "seq_movie",
            initialValue = 123434561,
            allocationSize = 100
    )
    private Long movieId;
    private String title;
    private String releaseDate;
    private String directedBy;
    private String writtenBy;
    private String runtime;
    @Column(length = 30000)
    private String tagline;
    @Column(nullable = false, updatable = false)
    private String language;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    @Column(length = 30000)
    private String synopsis;
    @OneToMany(mappedBy = "movie")
    private List<Review> reviewIds;

    @ManyToMany
    @JoinTable(
            name = "movies_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cast_id")
    )
    private List<Cast> movieCasts;
}
