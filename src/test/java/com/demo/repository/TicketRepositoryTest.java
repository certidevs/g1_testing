package com.demo.repository;

import com.demo.model.*;
import com.demo.model.enums.BuyStatus;
import com.demo.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests para TicketRepository")
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    private User user;
    private Session session;


    @BeforeEach
    void setUp() {
        // 1. Creamos y persistimos las dependencias necesarias
        user = User.builder().email("coder@demo.com")
                .firstName("pepe")
                .lastName("pepe")
                .username("pepe")
                .password("$2a$10$Bsw2uXDBS18RshkSkZDxOOTyWPG/KSQsjwwqXEfzusiMYT7qs528y")
                .role(Role.ROLE_USER).build();
        userRepository.save(user);

        Movie movie = Movie.builder().title("Interstellar").build();
        movieRepository.save(movie);

        session = Session.builder().movie(movie).build();
        sessionRepository.save(session);
    }

    @Test
    @DisplayName("Debería guardar un ticket y generar su ID")
    void guardarTicketTest() {
        Ticket ticket = Ticket.builder()
                .row("A")
                .seat("10")
                .price(12.50)
                .user(user)
                .session(session)
                .status(BuyStatus.LIBRE)
                .build();

        Ticket guardado = ticketRepository.save(ticket);

        assertNotNull(guardado.getId());
        assertEquals("A", guardado.getRow());
        assertEquals(BuyStatus.LIBRE, guardado.getStatus());
    }

    @Test
    @DisplayName("Debería encontrar tickets por el ID de la Sesión")
    void findBySessionIdTest() {
        // Guardamos un par de tickets para la misma sesión
        Ticket t1 = Ticket.builder().row("A").seat("1").session(session).user(user).build();
        Ticket t2 = Ticket.builder().row("A").seat("2").session(session).user(user).build();
        ticketRepository.saveAll(List.of(t1, t2));

        List<Ticket> encontrados = ticketRepository.findBySession_Id(session.getId());

        assertEquals(2, encontrados.size());
        assertTrue(encontrados.stream().allMatch(t -> t.getSession().getId().equals(session.getId())));
    }

    @Test
    @DisplayName("Debería encontrar tickets por el ID del Usuario")
    void findByUserIdTest() {
        Ticket ticket = Ticket.builder().row("B").seat("5").session(session).user(user).build();
        ticketRepository.save(ticket);

        List<Ticket> ticketsUsuario = ticketRepository.findByUser_IdOrderByBuyDateTimeDesc(user.getId());

        assertFalse(ticketsUsuario.isEmpty());
        assertEquals(user.getId(), ticketsUsuario.get(0).getUser().getId());
    }

    @Test
    @DisplayName("Debería encontrar tickets por el ID de la Película a través de la Sesión")
    void findByMovieIdTest() {
        Long movieId = session.getMovie().getId();
        Ticket ticket = Ticket.builder().row("C").seat("1").session(session).user(user).build();
        ticketRepository.save(ticket);

        List<Ticket> ticketsPelicula = ticketRepository.findBySession_Movie_Id(movieId);

        assertFalse(ticketsPelicula.isEmpty());
        assertEquals("Interstellar", ticketsPelicula.getFirst().getSession().getMovie().getTitle());
    }
}