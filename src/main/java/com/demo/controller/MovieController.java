package com.demo.controller;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<Movie> movies = movieRepository.findByActive(true);
        model.addAttribute("movies", movies);
        return "movies/movie-list";
    }

    //Ver las asociaciones que apuntan a movie (session y review)
    @GetMapping("movies/{id}")
    public String movieDetail(@PathVariable Long id, Model model){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()){
            Movie movie = movieOptional.get();
            model.addAttribute("movie", movie);
            return "movies/movie-detail";
        }
        return"redirect:/movies";

    }
    //Desactivar una pelicula
    @GetMapping("movies/deactivate/{id}")
    public String movieDeactivate(@PathVariable Long id, Model model){
        Optional<Movie> movieOptional = movieRepository.findById(id);

        if (movieOptional.isPresent()) {
            Movie movieDeactivate = movieOptional.get();
            movieDeactivate.setActive(false);
            movieRepository.save(movieDeactivate);
        }
        return "redirect:/movies";
    }

    //Creamos una pelicula
    @GetMapping("movies/new")
    public String newMovie(Model model){
        model.addAttribute("movie", new Movie());
        return "movies/movie-form";
    }

    //Editamos una pelicula existente
    @GetMapping("movies/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model){
        //Se pude hacer tambien con el Optional
        model.addAttribute("movie", movieRepository.findById(id).orElseThrow());
        return"movies/movie-form";
    }

    @PostMapping("movies")
    public String createMovie(@ModelAttribute Movie movie){
        movieRepository.save(movie);
        return "redirect:/movies/" + movie.getId();
    }


}
