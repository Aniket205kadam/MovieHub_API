package dev.aniket.MovieHub_API.controller;

import dev.aniket.MovieHub_API.model.Cast;
import dev.aniket.MovieHub_API.model.CastDto;
import dev.aniket.MovieHub_API.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cast")
public class CastController {
    private CastService castService;

    @Autowired
    public CastController(CastService castService) {
        this.castService = castService;
    }

    @GetMapping("/{castId}")
    public CastDto getCastById(@PathVariable Long castId) {
        return castService.getCastDtoById(castId);
    }

    @GetMapping("/cast-name/{castName}")
    public CastDto getCastByName(@PathVariable String castName) {
        return castService.getCastDtoByName(castName);
    }

    @GetMapping("/all")
    public List<Cast> getAllCast() {
        return castService.getAllCast();
    }
}
