package com.demo;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class G1TestingApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G1TestingApplication.class, args);
        //Datos de prueba
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        Movie p1 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p2 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p3 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p4 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p5 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p6 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p7 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p8 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p9 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p10 = movieRepository.save(Movie.builder().title("Interstellar").Director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
    }


}
