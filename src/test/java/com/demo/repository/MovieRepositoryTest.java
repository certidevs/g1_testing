package com.demo.repository;

import com.demo.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class MovieRepositoryTest {
    @Autowired
    MovieRepository movieRepository;

    @Test
    void guardarPelicula(){
        Movie movie = Movie.builder().director("Lee Toland Krieger").title("The Age of Adaline").durationMinutes(112).build();

        assertNotNull(movie.getId());
        assertEquals("The Age of Adaline", movie.getTitle());
    }
}
