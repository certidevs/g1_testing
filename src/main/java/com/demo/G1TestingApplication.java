package com.demo;

import com.demo.model.Movie;
import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import com.demo.repository.MovieRepository;
import com.demo.repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;

@SpringBootApplication
public class G1TestingApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(G1TestingApplication.class, args);
        //Datos de prueba
        MovieRepository movieRepository = context.getBean(MovieRepository.class);
        Movie p1 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p2 = movieRepository.save(Movie.builder().title("Inception").director("Christopher Nolan").durationMinutes(128).genre("Accion").build());
        Movie p3 = movieRepository.save(Movie.builder().title("Oppenheimer").director("Christopher Nolan").durationMinutes(180).genre("Historia").build());
        Movie p4 = movieRepository.save(Movie.builder().title("Inglourious Basterds").director("Quentin Tarantino").durationMinutes(153).genre("Historia").build());
        Movie p5 = movieRepository.save(Movie.builder().title("The Age of Adeline").director("Lee Toland Krieger").durationMinutes(112).genre("Drama").build());
        Movie p6 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p7 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p8 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p9 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p10 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());

        //Datos de prueba de las salas (Room)
        RoomRepository roomRepository = context.getBean(RoomRepository.class);
        roomRepository.saveAll(List.of(
                Room.builder().name("Sala 1").screenType(ScreenType.D4X).capacity(100).build(),
                Room.builder().name("Sala 2").screenType(ScreenType.IMAX).capacity(150).build(),
                Room.builder().screenType(ScreenType.D3).capacity(80).build(),
                Room.builder().screenType(ScreenType.D3).capacity(120).build(),
                Room.builder().screenType(ScreenType.STANDARD).capacity(90).build()
        ));
    }
}
