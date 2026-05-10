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
                Movie.builder().title("The Devil Wears Prada 2").director("David Frankel").releaseYear(2026).build(),
                Movie.builder().title("Michael").director("Antoine Fuqua").releaseYear(2026).build()
        ));
        movieToDeactivate = movieRepository.save(
            Movie.builder().active(true).title(" Looney Tunes").build()
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
                .andExpect(redirectedUrl("/movies"));

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

    @Test
    void moviesFull() throws Exception {
        //Se invoca EndPoint http://localhost:8080/movies, se lanza peticion HTTP Get al controles / movies
        //Luego verificamos que devuelve el status 200, que la vista es movies/movie-list
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/movie-list"));
                //.andExpect(model().attributeExists("movies",hasSize(3)));
    }
    @Test
    void movieEmpty() throws Exception{
        movieRepository.deleteAll();
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(view().name("movies/movie-list"))
                .andExpect(model().attribute("movies", hasSize(0)));
    }

}
