package com.demo.controller;

import com.demo.model.Movie;
import com.demo.model.Room;
import com.demo.model.Session;
import com.demo.model.Ticket;
import com.demo.model.User;
import com.demo.model.enums.BuyStatus;
import com.demo.repository.MovieRepository;
import com.demo.repository.RoomRepository;
import com.demo.repository.SessionRepository;
import com.demo.repository.TicketRepository;
import com.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Ticket ticket1;
    private Ticket ticket2;
    private Session session;
    private User user;

    @BeforeEach
    void setUp() {
        // Limpieza de datos respetando restricciones de integridad referencial
        ticketRepository.deleteAll();
        sessionRepository.deleteAll();
        roomRepository.deleteAll();
        movieRepository.deleteAll();
        userRepository.deleteAll();

        // 1. Crear entidades requeridas de apoyo
        Movie movie = movieRepository.save(Movie.builder().title("Batman").active(true).build());
        Room room = roomRepository.save(Room.builder().name("Sala 1").capacity(50).active(true).build());

        // !!! CORRECCIÓN AQUÍ: Agrega los campos que exige la validación de tu entidad User
        user = userRepository.save(User.builder()
                .username("cliente_test")
                .email("test@cinema.com")
                .firstName("Juan")          // <--- Requerido por la validación ("El nombre es obligatorio")
                .password(passwordEncoder.encode("user"))         // <--- Requerido por la validación ("La contraseña es obligatoria")
                .role(com.demo.model.enums.Role.ROLE_ADMIN) // Asegúrate de asignar el rol si tu enumerado o flujo lo requiere
                .build());

        session = sessionRepository.save(Session.builder()
                .movie(movie)
                .room(room)
                .price(10.0)
                .build());

        // 2. Población base de tickets para las pruebas
        ticket1 = ticketRepository.save(Ticket.builder()
                .session(session)
                .row("A") // Nota: Cambiado de .row() a .fila() para coincidir con la columna hibernate de tus logs
                .seat("1")
                .price(10.0)
                .user(user)
                .status(BuyStatus.LIBRE)
                .build());

        ticket2 = ticketRepository.save(Ticket.builder()
                .session(session)
                .row("A")
                .seat("2")
                .price(10.0)
                .status(BuyStatus.LIBRE)
                .build());
    }

    @Test
    void getTicketsFull() throws Exception {
        // Verifica que cargue correctamente la lista con los 2 tickets iniciales
        mockMvc.perform(get("/tickets").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets/ticket-list"))
                .andExpect(model().attributeExists("tickets"))
                .andExpect(model().attribute("tickets", hasSize(2)))
                .andExpect(model().attribute("numTickets", 2))
                .andExpect(model().attribute("title", "Listado de tickets"));
    }

    @Test
    void getTicketsEmpty() throws Exception {
        ticketRepository.deleteAll();

        mockMvc.perform(get("/tickets").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets/ticket-list"))
                .andExpect(model().attribute("tickets", hasSize(0)))
                .andExpect(model().attribute("numTickets", 0));
    }

    @Test
    // @WithMockUser(username = "admin_test", roles = {"ADMIN"}) // <--- Simula un Administrador
    @DisplayName("GET /tickets/{id}")
    void ticketDetailFound() throws Exception {
        Long ticketId = ticket1.getId();

        mockMvc.perform(get("/tickets/" + ticketId).with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets/ticket-detail"))
                .andExpect(model().attributeExists("ticket"))
                .andExpect(model().attribute("ticket", hasProperty("id", is(ticketId))))
                .andExpect(model().attribute("ticket", hasProperty("row", is("A"))))
                .andExpect(model().attribute("ticket", hasProperty("seat", is("1"))));
    }

    @Test
    void verEdicionTicket() throws Exception {
        Long ticketId = ticket1.getId();

        // El endpoint GET tickets/edit/{id} debe inyectar el ticket, la lista de usuarios y las sesiones
        mockMvc.perform(get("/tickets/edit/" + ticketId).with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("tickets/ticket-form"))
                .andExpect(model().attributeExists("ticket"))
                .andExpect(model().attributeExists("users"))
                .andExpect(model().attributeExists("sessions"))
                .andExpect(model().attribute("users", hasSize(1)))
                .andExpect(model().attribute("sessions", hasSize(1)));
    }

    @Disabled
    @Test
    @WithMockUser(username = "cliente_test", roles = {"USER"}) // <--- Simula un Usuario común
    @DisplayName("GET /tickets/buy/{id}")
    void buyTicketSuccess() throws Exception {
        Long ticketId = ticket2.getId();

        // Verificamos estado inicial antes del proceso de compra
        assertEquals(BuyStatus.LIBRE, ticket2.getStatus());
        assertNull(ticket2.getUser());

        // Simulamos la llamada pasando el modelo implícito o flash attribute si requiere mapear el @ModelAttribute User
        mockMvc.perform(get("/tickets/buy/" + ticketId).with(user(user))
                        .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/tickets/" + ticketId));

        // Obtenemos el registro actualizado de la base de datos para validar los cambios de la compra
        Ticket ticketComprado = ticketRepository.findById(ticketId).orElseThrow();

        assertEquals(BuyStatus.PAGADO, ticketComprado.getStatus());
        assertNotNull(ticketComprado.getBuyDateTime());
        assertEquals(user.getId(), ticketComprado.getUser().getId());
        assertEquals(10.0, ticketComprado.getPrice()); // Precio obtenido del Session asignado
    }
}