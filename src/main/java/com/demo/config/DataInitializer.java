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
        Movie m0 = movieRepo.save(Movie.builder().title("Top Gun: Ídolos del aire - (40 Aniversario)").director("Tony Scott")
                .sinopsis("La escuela de pilotos de élite de Estados Unidos prepara a los nuevos conductores de los F-14. En sus aulas, talento y ego chocarán entre dos jóvenes pilotos, Maverick y Iceman.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BZWYyZWY2NDctYjFlOC00ZjNjLWExYWEtZDA3MDllYWZmYTk5XkEyXkFqcGc@._V1_.jpg").durationMinutes(108).genre("Acción").build());

        Movie m1 = movieRepo.save(Movie.builder().title("Las ovejas detectives").director("Kyle Balda")
                .sinopsis("En esta ingeniosa y novedosa película de misterio, George (Hugh Jackman) es un pastor que cada noche lee novelas policíacas a sus queridas ovejas, dando por sentado que no pueden entenderlas. Pero cuando un misterioso incidente altera la vida en la granja, las ovejas se dan cuenta de que deben convertirse en detectives. A medida que siguen las pistas e investigan a los sospechosos humanos, demuestran que incluso las ovejas pueden ser brillantes resolviendo crímenes.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYTk0ZDI5NTAtYWQ5MS00MmI2LWIxMTktOWFkNTEwYzg0OWE4XkEyXkFqcGc@._V1_.jpg").durationMinutes(109).genre("Animación").build());

        Movie m2 = movieRepo.save(Movie.builder().title("The Mandalorian y Grogu").director("Jon Favreau").durationMinutes(132)
                .sinopsis("El malvado Imperio ha caído y los señores de la guerra imperiales siguen dispersos por toda la galaxia. Mientras la incipiente Nueva República trabaja para proteger todo por lo que luchó la Rebelión, ha reclutado la ayuda del legendario cazarrecompensas mandaloriano Din Djarin (Pedro Pascal) y su joven aprendiz Grogu.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BZmI1NzdjYTUtM2Y1MC00MDJmLWFlMmMtZDQzNGY1Y2E4NjA0XkEyXkFqcGc@._V1_.jpg").genre("Ciencia ficción").build());

        Movie m3 = movieRepo.save(Movie.builder().title("El drama").director("Kristoffer Borgli").durationMinutes(105)
                .sinopsis("Una pareja, en los días previos a su boda, se enfrenta a una crisis cuando unas inesperadas revelaciones desbaratan lo que uno de ellos creía saber sobre el otro.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMTcwOGZhYmItYTg3ZS00NjUwLWJiMmQtMjU5YjEwYWY0NmNkXkEyXkFqcGc@._V1_.jpg").genre("Romance").build());

        Movie m4 = movieRepo.save(Movie.builder().title("Proyecto salvación").director("Phil Lord").durationMinutes(156)
                .sinopsis("El profesor de ciencias Ryland Grace (Ryan Gosling) se despierta en una nave espacial a años luz de casa sin recordar quién es ni cómo ha llegado hasta allí. A medida que recupera la memoria, empieza a descubrir su misión: resolver el enigma de la misteriosa sustancia que provoca la extinción del sol. Deberá recurrir a sus conocimientos científicos y a sus ideas poco ortodoxas para salvar todo lo que hay en la Tierra de la extinción... pero una amistad inesperada significa que quizá no tenga que hacerlo solo.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYTJmNThhZGMtMmJiNC00MmQ5LWIzYzEtMmM4ZGM5NWY3NzRhXkEyXkFqcGc@._V1_.jpg").genre("Ciencia ficción").build());

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
                Room.builder().name("Sala 1").active(true).screenType(ScreenType.D4X).capacity(100).build(),
                Room.builder().name("Sala 2").active(true).screenType(ScreenType.IMAX).capacity(150).build(),
                Room.builder().name("Sala 3").active(false).screenType(ScreenType.D3).capacity(80).build(),
                Room.builder().screenType(ScreenType.D3).active(true).capacity(120).build(),
                Room.builder().screenType(ScreenType.STANDARD).active(true).capacity(90).build()
        ));
    }
}