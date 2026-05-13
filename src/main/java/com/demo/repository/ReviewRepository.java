package com.demo.repository;

import com.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    // TODO   findByMovieId  para mostrar en movie-detail.html
}