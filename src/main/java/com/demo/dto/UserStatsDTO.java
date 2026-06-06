package com.demo.dto;

import com.demo.model.Review;
import java.util.List;

// Estadísticas del usuario: número de reseñas escritas y su listado
public record UserStatsDTO(
        long countReviews,
        List<Review> reviews
) {}
