package com.demo.config;


import com.demo.model.*;
import com.demo.model.enums.BuyStatus;
import com.demo.model.enums.Role;
import com.demo.model.enums.ScreenType;
import com.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@AllArgsConstructor
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private RoomRepository roomRepo;
    private SessionRepository sessionRepo;
    private MovieRepository movieRepo;
    private TicketRepository ticketRepo;
    private UserRepository userRepo;
    private ReviewRepository reviewRepo;
    private PasswordEncoder passwordEncoder;

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
        var room1 = Room.builder().name("Sala 1").active(true).screenType(ScreenType.D4X).capacity(100).build();
        var room2 = Room.builder().name("Sala 2").active(true).screenType(ScreenType.IMAX).capacity(150).build();
        var room3 = Room.builder().name("Sala 3").active(false).screenType(ScreenType.D3).capacity(80).build();
        var room4 = Room.builder().name("Sala 4").screenType(ScreenType.D3).active(true).capacity(120).build();
        var room5 = Room.builder().name("Sala 5").screenType(ScreenType.STANDARD).active(true).capacity(100).build();
        var room6 = Room.builder().name("Sala 6").screenType(ScreenType.STANDARD).active(true).capacity(125).build();
        roomRepo.saveAll(List.of(
                room1, room2, room3, room4
        ));

                //Datos de prueba de las sesiones (Sessions)
                        sessionRepo.saveAll(List.of(
                                //Sesiones para la movie(m0)
                                sessionRepo.save(Session.builder().movie(m0).room(room1).price(12.50).language("VO").adMinutes(15).startTime(java.time.LocalDateTime.of(2026, 5, 20, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m0).room(room2).price(22.60).language("doblada").adMinutes(20).startTime(java.time.LocalDateTime.of(2026, 6, 30, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m0).room(room3).price(10.00).language("VOSE").adMinutes(25).startTime(java.time.LocalDateTime.of(2026, 5, 10, 19, 10)).build()),
                                sessionRepo.save(Session.builder().movie(m0).room(room4).price(50.00).language("VO").adMinutes(52).startTime(java.time.LocalDateTime.of(2026, 5, 30, 21, 15)).build()),

                                //Sesiones para la movie(m1)
                                sessionRepo.save(Session.builder().movie(m1).room(room1).price(32.50).language("VO").adMinutes(25).startTime(java.time.LocalDateTime.of(2026, 5, 21, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m1).room(room2).price(55.00).language("doblada").adMinutes(30).startTime(java.time.LocalDateTime.of(2026, 6, 11, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m1).room(room3).price(60.00).language("VOSE").adMinutes(40).startTime(java.time.LocalDateTime.of(2026, 5, 30, 19, 10)).build()),
                                sessionRepo.save(Session.builder().movie(m1).room(room4).price(19.50).language("doblada").adMinutes(19).startTime(java.time.LocalDateTime.of(2026, 5, 16, 21, 15)).build()),

                                //Sesiones para la movie(m2)
                                sessionRepo.save(Session.builder().movie(m2).room(room1).price(12.50).language("VO").adMinutes(35).startTime(java.time.LocalDateTime.of(2026, 5, 22, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m2).room(room2).price(24.10).language("doblada").adMinutes(20).startTime(java.time.LocalDateTime.of(2026, 6, 12, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m2).room(room3).price(30.20).language("VOSE").adMinutes(40).startTime(java.time.LocalDateTime.of(2026, 5, 22, 19, 7)).build()),
                                sessionRepo.save(Session.builder().movie(m2).room(room4).price(60.50).language("VO").adMinutes(10).startTime(java.time.LocalDateTime.of(2026, 5, 12, 21, 15)).build()),

                                //Sesiones para la movie(m3)
                                sessionRepo.save(Session.builder().movie(m3).room(room1).price(15.00).language("VO").adMinutes(20).startTime(java.time.LocalDateTime.of(2026, 5, 23, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m3).room(room2).price(20.20).language("doblada").adMinutes(17).startTime(java.time.LocalDateTime.of(2026, 5, 23, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m3).room(room3).price(30.50).language("VOSE").adMinutes(45).startTime(java.time.LocalDateTime.of(2026, 6, 13, 19, 20)).build()),
                                sessionRepo.save(Session.builder().movie(m3).room(room4).price(77.00).language("VO").adMinutes(20).startTime(java.time.LocalDateTime.of(2026, 5, 25, 21, 15)).build()),

                                //Sesiones para la movie(m4)
                                sessionRepo.save(Session.builder().movie(m4).room(room1).price(15.00).language("VO").adMinutes(30).startTime(java.time.LocalDateTime.of(2026, 5, 24, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m4).room(room2).price(7.00).language("doblada").adMinutes(5).startTime(java.time.LocalDateTime.of(2026, 5, 24, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m4).room(room3).price(10.99).language("VOSE").adMinutes(9).startTime(java.time.LocalDateTime.of(2026, 6, 14, 19, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m4).room(room4).price(5.50).language("VO").adMinutes(2).startTime(java.time.LocalDateTime.of(2026, 5, 22, 21, 15)).build()),

                                //Sesiones para la movie(m5)
                                sessionRepo.save(Session.builder().movie(m5).room(room1).price(5.00).language("VO").adMinutes(6).startTime(java.time.LocalDateTime.of(2026, 5, 25, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m5).room(room2).price(10.20).language("doblada").adMinutes(10).startTime(java.time.LocalDateTime.of(2026, 5, 25, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m5).room(room3).price(2.32).language("VOSE").adMinutes(4).startTime(java.time.LocalDateTime.of(2026, 5, 25, 19, 20)).build()),
                                sessionRepo.save(Session.builder().movie(m5).room(room4).price(8.88).language("VO").adMinutes(29).startTime(java.time.LocalDateTime.of(2026, 5, 25, 21, 15)).build()),

                                //Sesiones para la movie(m6)
                                sessionRepo.save(Session.builder().movie(m6).room(room1).price(10.05).language("VO").adMinutes(10).startTime(java.time.LocalDateTime.of(2026, 5, 26, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m6).room(room2).price(15.15).language("doblada").adMinutes(40).startTime(java.time.LocalDateTime.of(2026, 5, 26, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m6).room(room3).price(9.4).language("VOSE").adMinutes(5).startTime(java.time.LocalDateTime.of(2026, 5, 30, 19, 10)).build()),
                                sessionRepo.save(Session.builder().movie(m6).room(room4).price(25.19).language("VO").adMinutes(17).startTime(java.time.LocalDateTime.of(2026, 5, 16, 21, 15)).build()),

                                //Sesiones para la movie(m7)
                                sessionRepo.save(Session.builder().movie(m7).room(room1).price(10.00).language("VO").adMinutes(10).startTime(java.time.LocalDateTime.of(2026, 5, 27, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m7).room(room2).price(35.90).language("doblada").adMinutes(90).startTime(java.time.LocalDateTime.of(2026, 5, 27, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m7).room(room3).price(55.10).language("VOSE").adMinutes(8).startTime(java.time.LocalDateTime.of(2026, 5, 13, 19, 20)).build()),
                                sessionRepo.save(Session.builder().movie(m7).room(room4).price(5.50).language("VO").adMinutes(5).startTime(java.time.LocalDateTime.of(2026, 5, 25, 21, 15)).build()),

                                //Sesiones para la movie(m8)
                                sessionRepo.save(Session.builder().movie(m8).room(room1).price(10.00).language("VOSE").adMinutes(10).startTime(java.time.LocalDateTime.of(2026, 5, 28, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m8).room(room2).price(15.00).language("VO").adMinutes(20).startTime(java.time.LocalDateTime.of(2026, 5, 28, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m8).room(room3).price(20.00).language("VO").adMinutes(30).startTime(java.time.LocalDateTime.of(2026, 5, 28, 19, 20)).build()),
                                sessionRepo.save(Session.builder().movie(m8).room(room4).price(25.00).language("doblada").adMinutes(40).startTime(java.time.LocalDateTime.of(2026, 5, 28, 21, 15)).build()),

                                //Sesiones para la movie(m9)
                                sessionRepo.save(Session.builder().movie(m9).room(room1).price(11.00).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 29, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m9).room(room2).price(20.11).language("VOSE").adMinutes(97).startTime(java.time.LocalDateTime.of(2026, 5, 25, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m9).room(room3).price(5.60).language("doblada").adMinutes(55).startTime(java.time.LocalDateTime.of(2026, 5, 19, 19, 6)).build()),
                                sessionRepo.save(Session.builder().movie(m9).room(room4).price(15.30).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 29, 21, 15)).build()),

                                //Sesiones para la movie(m10)
                                sessionRepo.save(Session.builder().movie(m10).room(room1).price(11.00).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 10, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m10).room(room2).price(10.00).language("VOSE").adMinutes(97).startTime(java.time.LocalDateTime.of(2026, 5, 25, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m10).room(room3).price(9.00).language("doblada").adMinutes(55).startTime(java.time.LocalDateTime.of(2026, 5, 30, 19, 5)).build()),
                                sessionRepo.save(Session.builder().movie(m10).room(room4).price(8.00).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 20, 21, 15)).build()),

                                //Sesiones para la movie(m11)
                                sessionRepo.save(Session.builder().movie(m11).room(room1).price(11.00).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 31, 14, 30)).build()),
                                sessionRepo.save(Session.builder().movie(m11).room(room2).price(9.10).language("VOSE").adMinutes(21).startTime(java.time.LocalDateTime.of(2026, 5, 31, 16, 45)).build()),
                                sessionRepo.save(Session.builder().movie(m11).room(room3).price(7.50).language("doblada").adMinutes(55).startTime(java.time.LocalDateTime.of(2026, 5, 31, 19, 8)).build()),
                                sessionRepo.save(Session.builder().movie(m11).room(room4).price(11.00).language("VO").adMinutes(12).startTime(java.time.LocalDateTime.of(2026, 5, 31, 21, 15)).build())
                                ));



        // ==========================================
        // Datos de prueba de los USUARIOS (Users)
        // ==========================================
        var user1 = User.builder()
                .firstName("John")
                .lastName("Doe")
                .username("user1")
                .email("john.doe@example.com")
                .role(Role.ROLE_USER)
                .password(passwordEncoder.encode("user1")) // Idealmente usarías BCryptPasswordEncoder aquí si tienes seguridad configurada
                .build();

        var user2 = User.builder()
                .firstName("Jane")
                .lastName("Smith")
                .username("user2")
                .email("jane.smith@example.com")
                .role(Role.ROLE_USER)
                .password(passwordEncoder.encode("user2"))
                .build();

        var admin = User.builder()
                .firstName("Jane")
                .lastName("Smith")
                .username("admin")
                .email("admin@example.com")
                .role(Role.ROLE_ADMIN)
                .password(passwordEncoder.encode("admin"))
                .build();

        userRepo.saveAll(List.of(user1, user2, admin));

        List<Session> creadas = sessionRepo.findAll();

        if (!creadas.isEmpty()) {
            Session sesionSala1 = creadas.get(0); // Primera sesión de la Sala 1 (Top Gun)
            Session sesionSala2 = creadas.get(3); // Primera sesión de la Sala 2 (El drama)

            var ticket1 = Ticket.builder()
                    .row("A")
                    .seat("05")
                    .price(sesionSala1.getPrice())
                    .discount(0.0)
                    .status(com.demo.model.enums.BuyStatus.PAGADO) // Usamos el estado PAGADO para que se renderice bien en tu vista
                    .QRCode("QR_CODE_DATA_MOCK_1")
                    .user(user1)          // Asociamos al usuario 1
                    .session(sesionSala1) // Asociamos a la sesión de Top Gun
                    .buyDateTime(java.time.LocalDateTime.now())
                    .build();

            var ticket2 = Ticket.builder()
                    .row("B")
                    .seat("12")
                    .price(sesionSala1.getPrice())
                    .discount(2.0) // Un pequeño descuento de ejemplo
                    .status(com.demo.model.enums.BuyStatus.PAGADO)
                    .QRCode("QR_CODE_DATA_MOCK_2")
                    .user(user1)          // Mismo usuario, otra entrada
                    .session(sesionSala1)
                    .buyDateTime(java.time.LocalDateTime.now())
                    .build();

            var ticket3 = Ticket.builder()
                    .row("F")
                    .seat("22")
                    .price(sesionSala2.getPrice())
                    .discount(0.0)
                    .status(com.demo.model.enums.BuyStatus.INICIADO)
                    .QRCode("QR_CODE_DATA_MOCK_3")
                    .user(user2)          // Asociamos al usuario 2
                    .session(sesionSala2) // Otra película
                    .buyDateTime(java.time.LocalDateTime.now())
                    .build();


            var ticket4 = Ticket.builder()
                    .row("D")
                    .seat("05")
                    .price(sesionSala1.getPrice())
                    .discount(0.0)
                    .status(BuyStatus.LIBRE) // Usamos el estado PAGADO para que se renderice bien en tu vista
                    .QRCode("QR_CODE_DATA_MOCK_1")
                    .session(sesionSala1) // Asociamos a la sesión de Top Gun
                    .buyDateTime(LocalDateTime.now())
                    .build();

            ticketRepo.saveAll(List.of(ticket1, ticket2, ticket3, ticket4));
            System.out.println("TICKETS E HILOS DE PRUEBA INICIALIZADOS CORRECTAMENTE");

            reviewRepo.save(Review.builder().title("ok").rating(4).movie(m1).build());
            reviewRepo.save(Review.builder().title("bad").rating(1).movie(m2).build());
            reviewRepo.save(Review.builder().title("okk").rating(2).movie(m3).build());
        }
    }
}

