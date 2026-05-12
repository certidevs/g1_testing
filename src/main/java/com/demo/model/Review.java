package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    //título de la review
    private String title;

    //texto de la review
    @Column(length = 1000)
    private Integer description;

    //rating, de cinco
    private Integer rating;

    //película
    @ManyToOne
    private Movie movie;
}

