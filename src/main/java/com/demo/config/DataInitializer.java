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
        Movie m0 = movieRepo.save(Movie.builder().title("Top Gun: Ídolos del aire - (40 Aniversario)").director("Tony Scott").active(true).releaseYear(1986)
                .sinopsis("La escuela de pilotos de élite de Estados Unidos prepara a los nuevos conductores de los F-14. En sus aulas, talento y ego chocarán entre dos jóvenes pilotos, Maverick y Iceman.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BZWYyZWY2NDctYjFlOC00ZjNjLWExYWEtZDA3MDllYWZmYTk5XkEyXkFqcGc@._V1_.jpg").durationMinutes(108).genre("Accion").build());

        Movie m1 = movieRepo.save(Movie.builder().title("Las ovejas detectives").director("Kyle Balda").active(true).releaseYear(2026)
                .sinopsis("En esta ingeniosa y novedosa película de misterio, George (Hugh Jackman) es un pastor que cada noche lee novelas policíacas a sus queridas ovejas, dando por sentado que no pueden entenderlas. Pero cuando un misterioso incidente altera la vida en la granja, las ovejas se dan cuenta de que deben convertirse en detectives. A medida que siguen las pistas e investigan a los sospechosos humanos, demuestran que incluso las ovejas pueden ser brillantes resolviendo crímenes.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYTk0ZDI5NTAtYWQ5MS00MmI2LWIxMTktOWFkNTEwYzg0OWE4XkEyXkFqcGc@._V1_.jpg").durationMinutes(109).genre("Animación").build());

        Movie m2 = movieRepo.save(Movie.builder().title("The Mandalorian y Grogu").director("Jon Favreau").durationMinutes(132).active(true).releaseYear(2026)
                .sinopsis("El malvado Imperio ha caído y los señores de la guerra imperiales siguen dispersos por toda la galaxia. Mientras la incipiente Nueva República trabaja para proteger todo por lo que luchó la Rebelión, ha reclutado la ayuda del legendario cazarrecompensas mandaloriano Din Djarin (Pedro Pascal) y su joven aprendiz Grogu.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BZmI1NzdjYTUtM2Y1MC00MDJmLWFlMmMtZDQzNGY1Y2E4NjA0XkEyXkFqcGc@._V1_.jpg").genre("Ciencia ficción").build());

        Movie m3 = movieRepo.save(Movie.builder().title("El drama").director("Kristoffer Borgli").durationMinutes(105).active(true).releaseYear(2026)
                .sinopsis("Una pareja, en los días previos a su boda, se enfrenta a una crisis cuando unas inesperadas revelaciones desbaratan lo que uno de ellos creía saber sobre el otro.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMTcwOGZhYmItYTg3ZS00NjUwLWJiMmQtMjU5YjEwYWY0NmNkXkEyXkFqcGc@._V1_.jpg").genre("Romance").build());

        Movie m4 = movieRepo.save(Movie.builder().title("Proyecto salvación").director("Phil Lord").durationMinutes(156).active(true).releaseYear(2026)
                .sinopsis("El profesor de ciencias Ryland Grace (Ryan Gosling) se despierta en una nave espacial a años luz de casa sin recordar quién es ni cómo ha llegado hasta allí. A medida que recupera la memoria, empieza a descubrir su misión: resolver el enigma de la misteriosa sustancia que provoca la extinción del sol. Deberá recurrir a sus conocimientos científicos y a sus ideas poco ortodoxas para salvar todo lo que hay en la Tierra de la extinción... pero una amistad inesperada significa que quizá no tenga que hacerlo solo.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYTJmNThhZGMtMmJiNC00MmQ5LWIzYzEtMmM4ZGM5NWY3NzRhXkEyXkFqcGc@._V1_.jpg").genre("Ciencia ficción").build());

        Movie m5 = movieRepo.save(Movie.builder().title("La odisea").director("Christopher Nolan").durationMinutes(112).active(true).releaseYear(2026)
                .sinopsis("El legendario Odiseo enfrenta peligros épicos en su viaje de regreso a casa tras la Guerra de Troya. Se topa con criaturas míticas y dioses caprichosos, superando obstáculos increíbles antes de reunirse con su amada Penélope.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMjVjZGJiMWItNTkxNC00ODFiLTlkZDYtOGFmZmE2ODkxZmJlXkEyXkFqcGc@._V1_.jpg").genre("Accion").build());


        Movie m6 = movieRepo.save(Movie.builder().title("Todo lo que nunca fuimos").director("Jorge Alonso").durationMinutes(120).active(true).releaseYear(2026)
                .sinopsis("Tras perder a sus padres, Leah, una joven pintora, vive sumida en depresión. Su hermano le pide a Axel que cuide de ella mientras está fuera, sin saber que entre ellos surgirá un amor que podría cambiarlo todo.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYmI2NzVhZjMtOTgzOC00ZmMxLTkzMjctNjA4YTllN2Y0NjBhXkEyXkFqcGc@._V1_.jpg").genre("Romance").build());

        Movie m7 = movieRepo.save(Movie.builder().title("Toy Story 5").director("McKenna Harris").durationMinutes(90).active(true).releaseYear(2026)
                .sinopsis("Cuando Woody, Buzz, Jessie y la pandilla se encuentran con un recién llegado de alta tecnología, sus aventuras dan un giro inesperado mientras compiten por demostrar que los juguetes más valiosos de la vida no se limitan a códigos y chips.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMDQwZTM3NWUtNGI4Yi00YWRmLWExYWItNDNmNWVmYWEyZGUxXkEyXkFqcGc@._V1_.jpg").genre("Comedia").build());

        Movie m8 = movieRepo.save(Movie.builder().title("Playa de lobos").director("Javier Veiga").durationMinutes(100).active(true).releaseYear(2026)
                .sinopsis("Manu trabaja en un chiringuito. Klaus no suelta la última hamaca. Lo que parece un encuentro entre opuestos se vuelve sospechoso cuando Manu duda de Klaus. La tensión aumenta.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BNjlkZDdiYzMtZmU3OS00ODk2LTk5YzgtMTg0NzMxNTZhNmY0XkEyXkFqcGc@._V1_.jpg").genre("Comedia").build());

        Movie m9 = movieRepo.save(Movie.builder().title("El ser querido").director("Rodrigo Sorogoyen").durationMinutes(135).active(true).releaseYear(2026)
                .sinopsis("Un aclamado director se reencuentra con su distanciada hija, una actriz sin éxito, para rodar juntos una película, enfrentándose a su tensa relación y a asuntos del pasado sin resolver que ninguno de los dos quiere abordar directamente.")
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMmI3OTI5Y2ItZTJlMS00MjIwLTg5ZGUtOWFiZmUzOTE1NDFjXkEyXkFqcGc@._V1_.jpg").genre("Romance").build());


        Movie m10 = movieRepo.save(Movie.builder().active(true)
                .title(" The Super Mario Galaxy Movie").director("Aaron Horvath").durationMinutes(98).sinopsis("").genre("Adventure").releaseYear(2026)
                .imageUrl("https://m.media-amazon.com/images/M/MV5BMGIzMGI4OWYtNzdkMy00MjJlLWJiMDMtZjY1Y2UwMzQ0YzY3XkEyXkFqcGc@._V1_.jpg")
                .sinopsis("La película tiene lugar después de los acontecimientos de la primera, en la que dos hermanos, Mario y Luigi, y la princesa Peach emprenden una aventura hasta los confines del espacio y a través de la galaxia. Secuela de Super Mario Bros La película, basada en la saga Mario Galaxy.").build());

        Movie m11 = movieRepo.save(Movie.builder().active(true)
                .title(" The Devil Wears Prada 2").director("David Frankel").durationMinutes(119).genre("Comedia").releaseYear(2026)
                .imageUrl("https://m.media-amazon.com/images/M/MV5BYjZhNTE0ZTktYThlZC00OWUwLTlhMDItNzlkMjJkOGJhZTc5XkEyXkFqcGc@._V1_.jpg")
                .sinopsis("Casi veinte años después de interpretar a los icónicos personajes de Miranda, Andy, Emily y Nigel, Meryl Streep, Anne Hathaway, Emily Blunt y Stanley Tucci regresan a las calles de Nueva York y a las oficinas de la revista Runway en la secuela del fenómeno de 2006 que definió a toda una generación.").build());

        movieRepo.saveAll(List.of(m0, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11));

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