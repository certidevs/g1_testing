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
        //Datos de prueba de las PELICULAS
        MovieRepository movieRepository = context.getBean(MovieRepository.class);

        Movie p1 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie p2 = movieRepository.save(Movie.builder().title("Inception").director("Christopher Nolan").durationMinutes(128).genre("Accion").build());
        Movie p3 = movieRepository.save(Movie.builder().title("Oppenheimer").director("Christopher Nolan").durationMinutes(180).genre("Historia").build());
        Movie p4 = movieRepository.save(Movie.builder().title("Inglourious Basterds").director("Quentin Tarantino").durationMinutes(153).genre("Drama").build());
        Movie p5 = movieRepository.save(Movie.builder().title("The Age of Adeline").director("Lee Toland Krieger").durationMinutes(112).genre("Drama").build());
        Movie p6 = movieRepository.save(Movie.builder().title("Sr y Sra Smith").director("Doug Liman").durationMinutes(120).genre("Accion").build());
        Movie p7 = movieRepository.save(Movie.builder().title("The proposal").director("Anne Fletcher").durationMinutes(108).genre("Comedia").build());
        Movie p8 = movieRepository.save(Movie.builder().title("Love & Other Drugs").director("Edward Zwick").durationMinutes(112).genre("Ciencia ficcion").build());
        Movie p9 = movieRepository.save(Movie.builder().title("The Holiday").director("Nancy Meyers").durationMinutes(132).genre("Romance").build());
        Movie p10 = movieRepository.save(Movie.builder().title("Deadpool").director("Tim Miller").durationMinutes(108).genre("Comedia obscena").build());
        Movie p11 = movieRepository.save(Movie.builder().title("Joker").director("Todd Phillips").durationMinutes(122).genre("Thriller psicologico").build());
        Movie p12 = movieRepository.save(Movie.builder().title("The wolf of the wall street").director("Martin Scorsese").durationMinutes(180).genre("Comedia obscena").build());
        Movie p13 = movieRepository.save(Movie.builder().title("Fight Club").director("David Fincher").durationMinutes(139).genre("Thriller psicologico").build());
        Movie p14 = movieRepository.save(Movie.builder().title("Pulp Fiction").director("Quentin Tarantino").durationMinutes(154).genre("Comedia negra").build());
        Movie p15 = movieRepository.save(Movie.builder().title("Maleficent").director("Robert Stromberg").durationMinutes(97).genre("Comedia negra").build());
        Movie p16 = movieRepository.save(Movie.builder().title("Hacksaw Ridge").director("Mel Gibson").durationMinutes(139).genre("Docudrama").build());
        Movie p17 = movieRepository.save(Movie.builder().title("Saving Private Ryan").director("Steven Spielberg").durationMinutes(169).genre("Drama de epoca").build());
        Movie p18 = movieRepository.save(Movie.builder().title("Schindler's List").director("Steven Spielberg").durationMinutes(193).genre("Docudrama").build());
        Movie p19 = movieRepository.save(Movie.builder().title("Pulp Fiction").director("Quentin Tarantino").durationMinutes(154).genre("Comedia negra").build());

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
