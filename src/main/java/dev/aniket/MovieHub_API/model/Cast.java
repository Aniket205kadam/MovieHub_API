package dev.aniket.MovieHub_API.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seq_cast"
    )
    @SequenceGenerator(
            name = "seq_cast",
            sequenceName = "seq_cast",
            initialValue = 123434561,
            allocationSize = 123
    )
    private Long castId;
    private String castName;
    private String avatar;
    private Integer castAge;
    private String castHeight;
    private String castWeight;
    private String born; //4 April 1965 (age 59 years), Manhattan, New York, United States
    @ManyToMany(mappedBy = "movieCasts")
    @JsonIgnore
    private List<Movie> movies;

   //Social media Profiles links
    @Column(unique = true)
    private String instagram;
    @Column(unique = true)
    private String twitter;
    @Column(unique = true)
    private String facebook;

}
