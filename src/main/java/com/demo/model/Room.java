package com.demo.model;


import com.demo.model.enums.ScreenType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="salas")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    //nombre de la sala (Sala 1...)
    private String name;

    //número máximo de asientos disponibles en la sala
    private Integer capacity;

    //tipo de sala (IMAX, STANDARD, 3D, 4DX...)
    @Enumerated(EnumType.STRING)
    private ScreenType screenType;


    private Integer floorNumber;

}
