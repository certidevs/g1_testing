package com.demo.model;

import com.demo.model.enums.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class ReviewTest {

    private static final ZoneId DEFAULT_ZONE = ZoneId.of("Europe/Madrid");

    @Test
    void shouldCreateReviewWithNoArgsConstructor() {
        Review review = new Review();

        assertNull(review.getId());
        assertNull(review.getTitle());
        assertNull(review.getDescription());
        assertNull(review.getRating());
        assertNull(review.getMovie());
        assertNull(review.getUser());
    }

    @Test
    void shouldCreateReviewUsingBuilder() {
        Movie movie = Movie.builder()
                .id(1L)
                .title("Inception")
                .director("Christopher Nolan")
                .genre("Science Fiction")
                .releaseYear(2010)
                .durationMinutes(148)
                .active(true)
                .build();

        User user = User.builder()
                .id(1L)
                .username("review_user")
                .email("review@example.com")
                .firstName("Review")
                .lastName("User")
                .role(Role.ROLE_USER)
                .active(true)
                .build();

        LocalDateTime creationDate = LocalDateTime.now(DEFAULT_ZONE);

        Review review = Review.builder()
                .id(1L)
                .title("Great movie")
                .description("A very interesting and original film.")
                .rating(5)
                .creationDate(creationDate)
                .movie(movie)
                .user(user)
                .build();

        assertEquals(1L, review.getId());
        assertEquals("Great movie", review.getTitle());
        assertEquals("A very interesting and original film.", review.getDescription());
        assertEquals(5, review.getRating());
        assertEquals(creationDate, review.getCreationDate());
        assertEquals(movie, review.getMovie());
        assertEquals(user, review.getUser());
    }

    @Test
    void shouldUpdateReviewUsingSetters() {
        Review review = new Review();

        Movie movie = Movie.builder()
                .id(2L)
                .title("Interstellar")
                .build();

        User user = User.builder()
                .id(2L)
                .username("user")
                .role(Role.ROLE_USER)
                .active(true)
                .build();

        LocalDateTime creationDate = LocalDateTime.now(DEFAULT_ZONE);

        review.setId(2L);
        review.setTitle("Updated title");
        review.setDescription("Updated description");
        review.setRating(4);
        review.setCreationDate(creationDate);
        review.setMovie(movie);
        review.setUser(user);

        assertEquals(2L, review.getId());
        assertEquals("Updated title", review.getTitle());
        assertEquals("Updated description", review.getDescription());
        assertEquals(4, review.getRating());
        assertEquals(creationDate, review.getCreationDate());
        assertEquals(movie, review.getMovie());
        assertEquals(user, review.getUser());
    }

    @Test
    void shouldAllowReviewWithoutMovieOrUser() {
        Review review = Review.builder()
                .title("Anonymous review")
                .description("Review without associations")
                .rating(3)
                .build();

        assertEquals("Anonymous review", review.getTitle());
        assertEquals("Review without associations", review.getDescription());
        assertEquals(3, review.getRating());
        assertNull(review.getMovie());
        assertNull(review.getUser());
    }

    @Test
    void shouldStoreRatingValues() {
        Review review = new Review();

        review.setRating(1);
        assertEquals(1, review.getRating());

        review.setRating(5);
        assertEquals(5, review.getRating());
    }
}
