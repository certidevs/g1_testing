package com.demo.controller;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class MovieControllerTest {
    @Autowired //Se agrega una anotacion por cada repository que necesitemos aqui
    MovieRepository movieRepository;
    @Autowired
    MockMvc mockMvc;
    Movie movieToDeactivate;

    @BeforeEach
    void setUp(){
        movieRepository.deleteAll();
        movieRepository.saveAll(List.of(
                Movie.builder().active(true).title("The Dark Knight").genre("Action").durationMinutes(152).releaseYear(2008).director("Christopher Nolan").build(),
                Movie.builder().active(true).title("Inception").genre("Sci-Fi").durationMinutes(148).releaseYear(2010).director("Christopher Nolan").build(),
                Movie.builder().active(true).title("The Dark Knight Rises").genre("Action").durationMinutes(164).releaseYear(2012).director("Christopher Nolan").build(),
                Movie.builder().active(true).title("Interstellar").genre("Sci-Fi").durationMinutes(169).releaseYear(2014).director("Christopher Nolan").build(),
                Movie.builder().active(true).title("The Avengers").genre("Action").durationMinutes(143).releaseYear(2012).director("Joss Whedon").build(),
                Movie.builder().active(true).title("Into the Dark").genre("Drama").durationMinutes(132).releaseYear(2019).director("Blumhouse").build()
        ));

        movieToDeactivate = movieRepository.save(
        Movie.builder().active(true).title("El bar de Moe").build()
        );
    }

    @Test
    void createNewMovie() throws Exception{
        //Contamos la cantidad de peliculas ACTUAL
        long now = movieRepository.count();

        //Con mockMvc enviamos la pelicula nueva al controller
        mockMvc.perform(post("/movies")
                .param("title", "TitleMovie Test")
                .param("director","DirectorMovie Test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/movies/*"));

        //Contamos la cantidad de peliculas ACTUALIZADO
        long update = movieRepository.count();

        assertEquals(now + 1, update);
    }

    @Test
    void deactivateRestaurant() throws Exception{
        assertTrue(movieToDeactivate.getActive());

        Long id = movieToDeactivate.getId();

        mockMvc.perform(get("/movies/deactivate/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));
        //Traemos una movie de BD para comprobar que el estado de active es false
        Movie movieDB = movieRepository.findById(id).orElseThrow();
        assertFalse(movieDB.getActive());
    }

    //Las peliculas activas segun setUp son 7
    @Test
    void moviesFull() throws Exception {
        //Se invoca EndPoint http://localhost:8080/movies, se lanza peticion HTTP Get al controles / movies
        //Luego verificamos que devuelve el status 200, que la vista es movies/movie-list
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/movie-list"))
                .andExpect(model().attributeExists("movies"))
                .andExpect(model().attribute("movies", hasSize(7)));
    }

    @Test
    void movieEmpty() throws Exception{
        movieRepository.deleteAll();
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/movie-list"))
                .andExpect(model().attribute("movies", hasSize(0)));
    }

    @Test
    void filterMoviesByTitle() throws Exception{
        mockMvc.perform(get("/movies").param("title", "dark"))
                .andExpect(model().attribute("movies", hasSize(3)));
    }

    @Test
    void filterMoviesByGenre() throws Exception{
        mockMvc.perform(get("/movies").param("genre", "Action"))
                .andExpect(model().attribute("movies", hasSize(3)));
    }

    @Test
    void filterMoviesByDuration() throws Exception{
        mockMvc.perform(get("/movies").param("durationMinutes", "150"))
                .andExpect(model().attribute("movies", hasSize(3)));
    }

    @Test
    void filterMoviesByTitleAndGenre() throws Exception{
        mockMvc.perform(get("/movies").param("title", "dark").param("genre", "Action"))
                .andExpect(model().attribute("movies", hasSize(2)));
    }

    @Test
    void movieDetailIsPresentTrue() throws Exception{
        Movie movie = movieRepository.findAll().getFirst();
        Long movieId = movie.getId();

        mockMvc.perform(get("/movies/" + movieId))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/movie-detail"))
                .andExpect(model().attributeExists("movie"))
                .andExpect(model().attribute("movie", hasProperty("id", is(movieId))))
                .andExpect(model().attribute("movie", hasProperty("title", is(movie.getTitle()))));
    }

    @Test
    void movieDetailIsPresentFalse() throws Exception {
        Long idInexistente = 99999L;

        mockMvc.perform(get("/movies/" + idInexistente))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies"));
    }

}
