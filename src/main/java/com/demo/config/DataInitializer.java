package com.demo.config;


import com.demo.model.Movie;
import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import com.demo.repository.MovieRepository;
import com.demo.repository.RoomRepository;
import com.demo.repository.SessionRepository;
import com.demo.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private RoomRepository roomRepo;
    private SessionRepository sessionRepo;
    private MovieRepository movieRepo;
    private TicketRepository ticketRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("INICIALIZANDO INITIALIZER");

        //Datos de prueba de las PELICULAS
        Movie m0 = movieRepo.save(Movie.builder().title("Lilo & Stich").director("Dean Fleischer Camp").durationMinutes(108).genre("Adventure").build());
        Movie m1 = movieRepo.save(Movie.builder().title("Interstellar").director("Christopher Nolan").durationMinutes(169).genre("Ciencia ficcion").build());
        Movie m2 = movieRepo.save(Movie.builder().title("Inception").director("Christopher Nolan").durationMinutes(128).genre("Accion").build());
        Movie m3 = movieRepo.save(Movie.builder().title("Oppenheimer").director("Christopher Nolan").durationMinutes(180).genre("Historia").build());
        Movie m4 = movieRepo.save(Movie.builder().title("Inglourious Basterds").director("Quentin Tarantino").durationMinutes(153).genre("Drama").build());
        Movie m5 = movieRepo.save(Movie.builder().title("The Age of Adeline").director("Lee Toland Krieger").durationMinutes(112).genre("Drama").build());
        Movie m6 = movieRepo.save(Movie.builder().title("Sr y Sra Smith").director("Doug Liman").durationMinutes(120).genre("Accion").build());
        Movie m7 = movieRepo.save(Movie.builder().title("The proposal").director("Anne Fletcher").durationMinutes(108).genre("Comedy").build());
        Movie m8 = movieRepo.save(Movie.builder().title("Love & Other Drugs").director("Edward Zwick").durationMinutes(112).genre("Ciencia ficcion").build());
        Movie m9 = movieRepo.save(Movie.builder().title("The Holiday").director("Nancy Meyers").durationMinutes(132).genre("Romance").build());
        Movie m10 = movieRepo.save(Movie.builder().title("Deadpool").director("Tim Miller").durationMinutes(108).genre("Comedia obscena").build());
        Movie m11 = movieRepo.save(Movie.builder().title("Joker").director("Todd Phillips").durationMinutes(122).genre("Thriller psicologico").build());
        Movie m12 = movieRepo.save(Movie.builder().title("The wolf of the wall street").director("Martin Scorsese").durationMinutes(180).genre("Comedia obscena").build());
        Movie m13 = movieRepo.save(Movie.builder().title("Fight Club").director("David Fincher").durationMinutes(139).genre("Thriller psicologico").build());
        Movie m14 = movieRepo.save(Movie.builder().title("Pulp Fiction").director("Quentin Tarantino").durationMinutes(154).genre("Accion").build());
        Movie m15 = movieRepo.save(Movie.builder().title("Maleficent").director("Robert Stromberg").durationMinutes(97).genre("Adventure").build());
        Movie m16 = movieRepo.save(Movie.builder().title("Hacksaw Ridge").director("Mel Gibson").durationMinutes(139).genre("Docudrama").build());
        Movie m17 = movieRepo.save(Movie.builder().title("Saving Private Ryan").director("Steven Spielberg").durationMinutes(169).genre("Drama de epoca").build());
        Movie m18 = movieRepo.save(Movie.builder().title("Schindler's List").director("Steven Spielberg").durationMinutes(193).genre("Docudrama").build());

        Movie m19 = movieRepo.save(Movie.builder()
                .title(" The Super Mario Galaxy Movie").director("Aaron Horvath").durationMinutes(98).genre("Adventure").releaseYear(2026)
                .sinopsis("La película tiene lugar después de los acontecimientos de la primera, en la que dos hermanos, Mario y Luigi, y la princesa Peach emprenden una aventura hasta los confines del espacio y a través de la galaxia. Secuela de Super Mario Bros La película, basada en la saga Mario Galaxy.").build());
        Movie m20 = movieRepo.save(Movie.builder()
                .title(" The Devil Wears Prada 2").director("David Frankel").durationMinutes(119).genre("Comedy").releaseYear(2026)
                .sinopsis("Casi veinte años después de interpretar a los icónicos personajes de Miranda, Andy, Emily y Nigel, Meryl Streep, Anne Hathaway, Emily Blunt y Stanley Tucci regresan a las calles de Nueva York y a las oficinas de la revista Runway en la secuela del fenómeno de 2006 que definió a toda una generación.").build());

        movieRepo.saveAll(List.of(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20));

        //Datos de prueba de las salas (Room)
        roomRepo.saveAll(List.of(
                Room.builder().name("Sala 1").screenType(ScreenType.D4X).capacity(100).build(),
                Room.builder().name("Sala 2").screenType(ScreenType.IMAX).capacity(150).build(),
                Room.builder().name("Sala 3").screenType(ScreenType.D3).capacity(80).build(),
                Room.builder().screenType(ScreenType.D3).capacity(120).build(),
                Room.builder().screenType(ScreenType.STANDARD).capacity(90).build()
        ));
    }
}