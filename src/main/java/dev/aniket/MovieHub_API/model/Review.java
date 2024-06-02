package dev.aniket.MovieHub_API.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_review"
    )
    @SequenceGenerator(
            name = "seq_review",
            sequenceName = "seq_review",
            initialValue = 123434561,
            allocationSize = 100
    )
    private Long reviewId;
    @Column(length = 1000)
    private String reviewBody;
    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;
    @Column(nullable = false)
    private Double rating;
    private LocalDateTime timestamp;
    public Review(String reviewBody) {
        this.reviewBody = reviewBody;
    }
}
