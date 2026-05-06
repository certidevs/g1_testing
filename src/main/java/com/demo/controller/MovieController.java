package com.demo.controller;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class MovieController {
    //Inyectar el repositorio de movie
    private final MovieRepository movieRepository;

    //GetMapping de peliculas
    @GetMapping("movies")
    public String moviesList(Model model){
        List<Movie> movies = movieRepository.findAll();
        model.addAttribute("movies", movies);
        model.addAttribute("numMovies", movies.size());

        return "movies/movie-list";
    }
    //Ver las asociaciones que apuntan a movie (session y review)
    @GetMapping("movies/{id}")
    public String movieDetail(@PathVariable Long id, Model model){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()){
            Movie movie = movieOptional.get();
            model.addAttribute("movie", movieRepository.findById(id).orElseThrow());
            return "movies/movie-detail";
        }
        return"redirect:/movies";

    }

}
