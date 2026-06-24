package com.demo.controller;

import com.demo.model.Session;
import com.demo.model.Ticket;
import com.demo.model.enums.BuyStatus;
import com.demo.repository.SessionRepository;
import com.demo.repository.TicketRepository;
import com.demo.model.Movie;
import com.demo.model.Review;
import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.repository.MovieRepository;
import com.demo.repository.ReviewRepository;
import com.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired private SessionRepository sessionRepository;
    @Autowired private TicketRepository ticketRepository;

    private Movie movie;
    private User user;
    private User otherUser;
    private Review review;
    private User admin;

    @BeforeEach
    void setUp() {

        reviewRepository.deleteAll();
        movieRepository.deleteAll();
        userRepository.deleteAll();

        movie = movieRepository.save(
                Movie.builder()
                        .title("Interstellar")
                        .active(true)
                        .build()
        );

        user = userRepository.save(
                User.builder()
                        .username("user_test")
                        .email("user@test.com")
                        .firstName("Juan")
                        .password(passwordEncoder.encode("1234"))
                        .role(Role.ROLE_USER)
                        .build()
        );

        Session session = sessionRepository.save(
                Session.builder().movie(movie).price(10.0).build());

        ticketRepository.save(
                Ticket.builder().user(user).session(session).status(BuyStatus.PAGADO).build());

        otherUser = userRepository.save(
                User.builder()
                        .username("other_test")
                        .email("other@test.com")
                        .firstName("Pedro")
                        .password(passwordEncoder.encode("1234"))
                        .role(Role.ROLE_USER)
                        .build()
        );

        admin = userRepository.save(
                User.builder()
                        .username("admin")
                        .email("admin@test.com")
                        .firstName("Admin")
                        .password(passwordEncoder.encode("1234"))
                        .role(Role.ROLE_ADMIN)
                        .build()
        );

        review = reviewRepository.save(
                Review.builder()
                        .title("Gran película")
                        .description("Muy recomendable")
                        .rating(5)
                        .movie(movie)
                        .user(user)
                        .build()
        );
    }

    // Verificamos que GET /reviews devuelve el listado de reviews
    @Test
    void reviews_shouldReturnReviewListView() throws Exception {

        mockMvc.perform(get("/reviews").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews/review-list"))
                .andExpect(model().attributeExists("reviews"));
    }

    // Verificamos que GET /reviews/{id} carga el detalle correctamente
    @Test
    void reviewDetail_shouldReturnDetailView() throws Exception {

        mockMvc.perform(get("/reviews/" + review.getId()).with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews/review-detail"))
                .andExpect(model().attributeExists("review"));
    }

    // Verificamos que GET /reviews/new devuelve el formulario vacío
    @Test
    void newReview_shouldReturnForm() throws Exception {

        mockMvc.perform(get("/reviews/new")
                        .param("movieId", movie.getId().toString())
                        .with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews/review-form"))
                .andExpect(model().attributeExists("review"));
    }

    // Verificamos que POST /reviews guarda una nueva review en BD
    @Test
    void saveReview_shouldCreateReview() throws Exception {

        long now = reviewRepository.count();

        mockMvc.perform(post("/reviews")
                        .with(user(user)).with(csrf())
                        .param("title", "Nueva review")
                        .param("description", "Muy buena")
                        .param("rating", "4")
                        .param("movieId", movie.getId().toString()))
                .andExpect(status().is3xxRedirection());

        long updated = reviewRepository.count();

        assertEquals(now + 1, updated);
    }

    // Verificamos que el autor de la review puede editarla
    @Test
    void editReview_shouldAllowOwner() throws Exception {

        mockMvc.perform(get("/reviews/edit/" + review.getId())
                        .with(user(user)))
                .andExpect(status().isOk())
                .andExpect(view().name("reviews/review-form"));
    }

    // Verificamos que un usuario que no es el autor es redirigido
    @Test
    void editReview_shouldRedirectNonOwner() throws Exception {

        mockMvc.perform(get("/reviews/edit/" + review.getId())
                        .with(user(otherUser)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reviews/" + review.getId()));
    }

    // Verificamos que un admin puede eliminar una review
    @Test
    void deleteReview_shouldDeleteReview() throws Exception {

        Long id = review.getId();

        mockMvc.perform(get("/reviews/delete/" + id)
                        .with(user(admin)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/reviews"));

        assertThat(reviewRepository.findById(id)).isEmpty();
    }

    @Test
    void saveReview_shouldRejectWithoutTicket() throws Exception {
        long now = reviewRepository.count();

        mockMvc.perform(post("/reviews")
                        .with(user(otherUser)).with(csrf())
                        .param("title", "X").param("description", "Y").param("rating", "3")
                        .param("movieId", movie.getId().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/movies/" + movie.getId()));

        assertEquals(now, reviewRepository.count()); // NO se creó la reseña
    }


}