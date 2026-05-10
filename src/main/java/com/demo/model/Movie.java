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

    @Builder.Default
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    private Integer durationMinutes;
    private String genre;
    private String imageUrl;
    private Integer releaseYear;
}
