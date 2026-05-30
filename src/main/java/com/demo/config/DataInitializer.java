package com.demo.config;

import com.demo.model.*;
import com.demo.model.enums.BuyStatus;
import com.demo.model.enums.Role;
import com.demo.model.enums.ScreenType;
import com.demo.repository.*;
import com.demo.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
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
    private TicketService ticketService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("-> Inicializando datos de prueba...");

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


        //Datos de prueba de las salas (Room)
        // Precios por tipo de sala (aplicados en sesiones):
        //   D4X  (Sala 1) → 13,00 €
        //   IMAX (Sala 2) → 14,00 €
        //   D3   (Sala 4) → 11,00 €
        // ══════════════════════════════════════════════════════════════════════
        var room1 = Room.builder().name("Sala 1").active(true).screenType(ScreenType.D4X).capacity(100).build();
        var room2 = Room.builder().name("Sala 2").active(true).screenType(ScreenType.IMAX).capacity(150).build();
        var room3 = Room.builder().name("Sala 3").active(false).screenType(ScreenType.D3).capacity(80).build();
        var room4 = Room.builder().name("Sala 4").screenType(ScreenType.D3).active(true).capacity(60).build();
        var room5 = Room.builder().name("Sala 5").screenType(ScreenType.STANDARD).active(true).capacity(100).build();
        var room6 = Room.builder().name("Sala 6").screenType(ScreenType.STANDARD).active(true).capacity(125).build();
        roomRepo.saveAll(List.of(room1, room2, room3, room4));

        // ==========================================
        // Datos de prueba de los USUARIOS (Users)
        // ==========================================
        var user = userRepo.save(User.builder()
                .firstName("Alejandro").lastName("Mendoza").username("user")
                .email("alex_m98@gmail.com").role(Role.ROLE_USER).active(true)
                .password(passwordEncoder.encode("user")).build());

        var user2 = userRepo.save(User.builder()
                .firstName("Maria").lastName("Fernandez").username("user2")
                .email("maria.fernandez@gmail.com").role(Role.ROLE_USER)
                .password(passwordEncoder.encode("user2")).build());

        userRepo.save(User.builder()
                .firstName("Admin").lastName("Cinema").username("admin")
                .email("admin@cinema.com").role(Role.ROLE_ADMIN).active(true).imageUrl("/uploads/lobo.png")
                .password(passwordEncoder.encode("admin")).build());

        // ==========================================
        // SESIONES PASADAS (para historial de tickets de user1)
        //
        //  pasada1     → hace 7 días → La odisea        (4DX,  13€) → user1 compró 2
        //  pasada2     → hace 5 días → Proyecto salv.   (IMAX, 14€) → user1 compró 1
        //  pasada3     → hace 3 días → The Mandalorian  (3D,   11€) → user1 compró 3
        //  sesAgotada  → hace 1 día  → Toy Story 5      (IMAX, 14€) → todos PAGADO
        // ==========================================
        LocalDate hoy = LocalDate.now();

        Session pasada1 = sessionRepo.save(Session.builder()
                .movie(m5).room(room1).price(13.00)
                .language("doblada").adMinutes(15)
                .startTime(hoy.minusDays(7).atTime(19, 0)).build());

        Session pasada2 = sessionRepo.save(Session.builder()
                .movie(m4).room(room2).price(14.00)
                .language("VO").adMinutes(20)
                .startTime(hoy.minusDays(5).atTime(21, 0)).build());

        Session pasada3 = sessionRepo.save(Session.builder()
                .movie(m2).room(room4).price(11.00)
                .language("VOSE").adMinutes(15)
                .startTime(hoy.minusDays(3).atTime(18, 30)).build());

        Session sesAgotada = sessionRepo.save(Session.builder()
                .movie(m7).room(room2).price(14.00)
                .language("doblada").adMinutes(10)
                .startTime(hoy.minusDays(1).atTime(16, 0)).build());

        // SESIONES FUTURAS que se crean dinamicamente con respecto a hoy - próximos 8 días(vamos, relativas a hoy)
        //
        //  Sala 1 · 4DX  · 13€ → La odisea / Mandalorian / Proyecto salvación
        //  Sala 2 · IMAX · 14€ → Super Mario / La odisea / Top Gun
        //                        + Toy Story en pase de mañana solo fines de semana
        //  Sala 4 · 3D   · 11€ → Devil Wears Prada / Playa de lobos / El drama
        //                        + matinal alternando Las ovejas y tod lo que nunca fuimos
        //  Sala 4 pases únicos → El ser querido (días 2 y 5)
        List<Session> sesionesFuturas = new ArrayList<>();

        for (int dia = 0; dia <= 7; dia++) {
            LocalDate fecha = hoy.plusDays(dia);

            // Sesiones de la sala 1 · 4DX · 13 € -----------------------------------------
            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m5).room(room1).price(13.00)
                    .language("doblada").adMinutes(15)
                    .startTime(fecha.atTime(16, 30)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m2).room(room1).price(13.00)
                    .language("VO").adMinutes(20)
                    .startTime(fecha.atTime(19, 0)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m4).room(room1).price(13.00)
                    .language("VOSE").adMinutes(15)
                    .startTime(fecha.atTime(21, 30)).build()));

            // Sesiones de la sala 2 · IMAX · 14 € -----------------------------------------
            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m10).room(room2).price(14.00)
                    .language("doblada").adMinutes(15)
                    .startTime(fecha.atTime(16, 0)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m5).room(room2).price(14.00)
                    .language("VO").adMinutes(15)
                    .startTime(fecha.atTime(18, 30)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m0).room(room2).price(14.00)
                    .language("VOSE").adMinutes(15)
                    .startTime(fecha.atTime(21, 0)).build()));

            // Toy Story en IMAX: pase matinal solo sábados y domingos
            int diaSemana = fecha.getDayOfWeek().getValue(); // 1=lun … 7=dom
            if (diaSemana == 6 || diaSemana == 7) {
                sesionesFuturas.add(sessionRepo.save(Session.builder()
                        .movie(m7).room(room2).price(14.00)
                        .language("doblada").adMinutes(10)
                        .startTime(fecha.atTime(11, 0)).build()));
            }

            // Sesiones de la sala 4 · 3D · 11 € -----------------------------------------
            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m11).room(room4).price(11.00)
                    .language("VO").adMinutes(15)
                    .startTime(fecha.atTime(16, 30)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m8).room(room4).price(11.00)
                    .language("VO").adMinutes(12)
                    .startTime(fecha.atTime(19, 0)).build()));

            sesionesFuturas.add(sessionRepo.save(Session.builder()
                    .movie(m3).room(room4).price(11.00)
                    .language("VOSE").adMinutes(12)
                    .startTime(fecha.atTime(21, 15)).build()));

            // Matinal sala 4: alternando película según día par/impar
            if (dia % 2 == 0) {
                sesionesFuturas.add(sessionRepo.save(Session.builder()
                        .movie(m1).room(room4).price(11.00)
                        .language("doblada").adMinutes(10)
                        .startTime(fecha.atTime(11, 0)).build()));
            } else {
                sesionesFuturas.add(sessionRepo.save(Session.builder()
                        .movie(m6).room(room4).price(11.00)
                        .language("VO").adMinutes(13)
                        .startTime(fecha.atTime(11, 0)).build()));
            }
        }

        // El ser querido: pase único los días 2 y 5
        sesionesFuturas.add(sessionRepo.save(Session.builder()
                .movie(m9).room(room4).price(11.00)
                .language("VO").adMinutes(18)
                .startTime(hoy.plusDays(2).atTime(11, 30)).build()));

        sesionesFuturas.add(sessionRepo.save(Session.builder()
                .movie(m9).room(room4).price(11.00)
                .language("VOSE").adMinutes(18)
                .startTime(hoy.plusDays(5).atTime(11, 30)).build()));

        // GENERAR TICKETS para todas las sesiones
        ticketService.generarTickets(pasada1);
        ticketService.generarTickets(pasada2);
        ticketService.generarTickets(pasada3);
        ticketService.generarTickets(sesAgotada);

        for (Session s : sesionesFuturas) {
            ticketService.generarTickets(s);
        }

        // MARCAR TICKETS COMPRADOS (historial de user1)
        // pasada1 → user1 compró 1 entradas (La odisea, hace 7 días)
        List<Ticket> tPasada1 = ticketRepo.findBySession_Id(pasada1.getId());
        if (tPasada1.size() >= 2) {
            tPasada1.get(0).setStatus(BuyStatus.PAGADO);
            tPasada1.get(0).setUser(user);
            tPasada1.get(0).setBuyDateTime(pasada1.getStartTime().minusHours(2));
            //tPasada1.get(1).setStatus(BuyStatus.PAGADO);
            //tPasada1.get(1).setUser(user1);
           //tPasada1.get(1).setBuyDateTime(pasada1.getStartTime().minusHours(2));
            //ticketRepo.saveAll(List.of(tPasada1.get(0), tPasada1.get(1)));
            ticketRepo.save(tPasada1.get(0));
        }

        // pasada2 → user1 compró 1 entrada (Proyecto salvación, hace 5 días)
        List<Ticket> tPasada2 = ticketRepo.findBySession_Id(pasada2.getId());
        if (!tPasada2.isEmpty()) {
            tPasada2.get(0).setStatus(BuyStatus.PAGADO);
            tPasada2.get(0).setUser(user);
            tPasada2.get(0).setBuyDateTime(pasada2.getStartTime().minusDays(1));
            ticketRepo.save(tPasada2.get(0));
        }

        // pasada3 → user1 compró 3 entradas (The Mandalorian, hace 3 días)
        List<Ticket> tPasada3 = ticketRepo.findBySession_Id(pasada3.getId());
        if (!tPasada3.isEmpty()) {
            tPasada3.get(0).setStatus(BuyStatus.PAGADO);
            tPasada3.get(0).setUser(user);
            tPasada3.get(0).setBuyDateTime(pasada2.getStartTime().minusDays(1));
            ticketRepo.save(tPasada3.get(0));
        }
        /*
        if (tPasada3.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                tPasada3.get(i).setStatus(BuyStatus.PAGADO);
                tPasada3.get(i).setUser(user1);
                tPasada3.get(i).setBuyDateTime(pasada3.getStartTime().minusHours(6));
            }
            ticketRepo.saveAll(tPasada3.subList(0, 3));
        }*/

        // sesAgotada → TODOS los tickets PAGADO (demuestra el futuro badge "Agotada")
        List<Ticket> tAgotados = ticketRepo.findBySession_Id(sesAgotada.getId());
        tAgotados.forEach(t -> t.setStatus(BuyStatus.PAGADO));
        ticketRepo.saveAll(tAgotados);

        // REVIEWS - description omitido a propósito (bug #20: campo es Integer)
        reviewRepo.save(Review.builder().title("Nolan lo vuelve a hacer").rating(5).movie(m5).build());
        reviewRepo.save(Review.builder().title("Espectacular en IMAX, obligatoria").rating(5).movie(m5).build());
        reviewRepo.save(Review.builder().title("Grogu para siempre").rating(4).movie(m2).build());
        reviewRepo.save(Review.builder().title("Muy emotiva y divertida").rating(4).movie(m7).build());
        reviewRepo.save(Review.builder().title("Entretenida aunque algo lenta").rating(3).movie(m4).build());
        reviewRepo.save(Review.builder().title("Me ha encantado").rating(5).movie(m1).build());
        reviewRepo.save(Review.builder().title("No es para mí, demasiado lenta").rating(2).movie(m3).build());
        reviewRepo.save(Review.builder().title("Muy buena para toda la familia").rating(5).movie(m10).build());
        reviewRepo.save(Review.builder().title("Meryl Streep insuperable").rating(4).movie(m11).build());

        System.out.println("DataInitializer completado - " +
                movieRepo.count() + " películas, " +
                roomRepo.count() + " salas, " +
                sessionRepo.count() + " sesiones, " +
                ticketRepo.count() + " tickets.");
    }
}