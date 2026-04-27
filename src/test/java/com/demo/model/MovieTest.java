package com.demo.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {
    @Test
    void builderTest(){
        Movie movie = Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("ciencia ficcion").build();
        assertEquals("Interstellar", movie.getTitle());
        assertEquals(169, movie.getDurationMinutes());
    }

}
