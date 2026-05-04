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

        Movie m0 = movieRepository.save(Movie.builder().title("Lilo & Stich").director("Dean Fleischer Camp").durationMinutes(108).genre("Adventure").build());
        Movie m1 = movieRepository.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie m2 = movieRepository.save(Movie.builder().title("Inception").director("Christopher Nolan").durationMinutes(128).genre("Accion").build());
        Movie m3 = movieRepository.save(Movie.builder().title("Oppenheimer").director("Christopher Nolan").durationMinutes(180).genre("Historia").build());
        Movie m4 = movieRepository.save(Movie.builder().title("Inglourious Basterds").director("Quentin Tarantino").durationMinutes(153).genre("Drama").build());
        Movie m5 = movieRepository.save(Movie.builder().title("The Age of Adeline").director("Lee Toland Krieger").durationMinutes(112).genre("Drama").build());
        Movie m6 = movieRepository.save(Movie.builder().title("Sr y Sra Smith").director("Doug Liman").durationMinutes(120).genre("Accion").build());
        Movie m7 = movieRepository.save(Movie.builder().title("The proposal").director("Anne Fletcher").durationMinutes(108).genre("Comedy").build());
        Movie m8 = movieRepository.save(Movie.builder().title("Love & Other Drugs").director("Edward Zwick").durationMinutes(112).genre("Ciencia ficcion").build());
        Movie m9 = movieRepository.save(Movie.builder().title("The Holiday").director("Nancy Meyers").durationMinutes(132).genre("Romance").build());
        Movie m10 = movieRepository.save(Movie.builder().title("Deadpool").director("Tim Miller").durationMinutes(108).genre("Comedia obscena").build());
        Movie m11 = movieRepository.save(Movie.builder().title("Joker").director("Todd Phillips").durationMinutes(122).genre("Thriller psicologico").build());
        Movie m12 = movieRepository.save(Movie.builder().title("The wolf of the wall street").director("Martin Scorsese").durationMinutes(180).genre("Comedia obscena").build());
        Movie m13 = movieRepository.save(Movie.builder().title("Fight Club").director("David Fincher").durationMinutes(139).genre("Thriller psicologico").build());
        Movie m14 = movieRepository.save(Movie.builder().title("Pulp Fiction").director("Quentin Tarantino").durationMinutes(154).genre("Accion").build());
        Movie m15 = movieRepository.save(Movie.builder().title("Maleficent").director("Robert Stromberg").durationMinutes(97).genre("Adventure").build());
        Movie m16 = movieRepository.save(Movie.builder().title("Hacksaw Ridge").director("Mel Gibson").durationMinutes(139).genre("Docudrama").build());
        Movie m17 = movieRepository.save(Movie.builder().title("Saving Private Ryan").director("Steven Spielberg").durationMinutes(169).genre("Drama de epoca").build());
        Movie m18 = movieRepository.save(Movie.builder().title("Schindler's List").director("Steven Spielberg").durationMinutes(193).genre("Docudrama").build());

        Movie m19 = movieRepository.save(Movie.builder()
                .title(" The Super Mario Galaxy Movie").director("Aaron Horvath").durationMinutes(98).genre("Adventure").releaseYear(2026)
                .sinopsis("La película tiene lugar después de los acontecimientos de la primera, en la que dos hermanos, Mario y Luigi, y la princesa Peach emprenden una aventura hasta los confines del espacio y a través de la galaxia. Secuela de Super Mario Bros La película, basada en la saga Mario Galaxy.").build());
        Movie m20 = movieRepository.save(Movie.builder()
                .title(" The Devil Wears Prada 2").director("David Frankel").durationMinutes(119).genre("Comedy").releaseYear(2026)
                .sinopsis("Casi veinte años después de interpretar a los icónicos personajes de Miranda, Andy, Emily y Nigel, Meryl Streep, Anne Hathaway, Emily Blunt y Stanley Tucci regresan a las calles de Nueva York y a las oficinas de la revista Runway en la secuela del fenómeno de 2006 que definió a toda una generación.").build());

        movieRepository.saveAll(List.of(m0,m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16,m17,m18,m19,m20));

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
