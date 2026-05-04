package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;
    @Column(length = 600)
    private String sinopsis;
    private Integer durationMinutes;
    private String genre;
    private String imageUrl;
    private Integer releaseYear;

    @ToString.Exclude
    @ManyToOne
    private Session session;
}
