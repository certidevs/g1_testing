package com.demo.controller;

import com.demo.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@AllArgsConstructor
public class MovieController {
    //Inyectar el repositorio de movie
    private final MovieRepository movieRepository;

    //GetMapping de peliculas
    @GetMapping("movies")
    public String movies(Model model){
        model.addAttribute("movies", movieRepository.findAll());
        return "movies/movie-list";
    }

    @GetMapping("movies/{id}")
    public String movieDetail(@PathVariable Long id, Model model){
        return "movies/movie-detail";
    }

}
