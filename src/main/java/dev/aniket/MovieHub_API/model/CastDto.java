package dev.aniket.MovieHub_API.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CastDto {
    private Long castId;
    private String castName;
    private String avatar;
    private Integer castAge;
    private String castHeight;
    private String castWeight;
    private String born;
    private List<MovieDto> movies;
    private String instagram;
    private String twitter;
    private String facebook;
}
