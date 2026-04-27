package com.demo.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name = "sessions")
@AllArgsConstructor
@ToString

public class session {
    @Id
    //identificador único para cada sesión.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // fecha y hora de inicio de la sesión, se asigna automáticamente al crear una nueva sesión.
    private LocalDateTime startTime;

    //precio de la entrada para esta sesión (12.50, 15.00, etc.)
    private Double price;

    //idioma ("VO", "doblada", "VOSE") (Podría ser enum)
    private String language;





}
