package com.demo.repository;

import com.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovieId(Long movieId);

    long countByUser_Id(Long id);
    List<Review> findByUser_Id(Long id);

    List<Review> findByRatingGreaterThanEqual(Integer rating);
}