package com.demo.dto;

import com.demo.model.Review;
import com.demo.model.Ticket;
import java.util.List;

// Estadísticas del usuario: número de reseñas escritas, su listado y tickets comprados
public record UserStatsDTO(
        long countReviews,
        List<Review> reviews,
        List<Ticket> tickets
) {}
