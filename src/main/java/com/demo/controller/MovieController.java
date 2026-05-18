package com.demo.controller;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import com.demo.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class MovieController {
    //Inyectar el repositorio de movie
    private final MovieRepository movieRepository;
    private final SessionRepository sessionRepository;

    //GetMapping de peliculas
    @GetMapping("movies")
    public String moviesList(Model model,
                             @RequestParam(required = false) Integer durationMinutes,
                             @RequestParam(required = false) String genre,
                             @RequestParam(required = false) Integer releaseYear,
                             @RequestParam(required = false) String title
    ){
        List<Movie> movies = movieRepository.findActiveFiltering(durationMinutes, genre, releaseYear, title);
        model.addAttribute("movies", movies);
        model.addAttribute("numMovies", movies.size());
        model.addAttribute("title", "Listado de peliculas");
        return "movies/movie-list";
    }

    //Ver las asociaciones que apuntan a movie (session y review)
    @GetMapping("movies/{id}")
    public String movieDetail(@PathVariable Long id, Model model){
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if(movieOptional.isPresent()){
            Movie movie = movieOptional.get();
            model.addAttribute("movie", movie);
            model.addAttribute("sessions", sessionRepository.findByMovie_Id(id));
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
