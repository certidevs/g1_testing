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
    @Column(nullable = false)
    private String name;

    //número máximo de asientos disponibles en la sala
    //ToDo poner que la capacidad sea > 10
    @Column(nullable = false)
    private Integer capacity;

    //tipo de sala (IMAX, STANDARD, 3D, 4DX...)
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ScreenType screenType = ScreenType.STANDARD;

    @Column(columnDefinition = "BOOLEAN DEFAULT true") //por defecto será true cuando se inserta en base de datos
    private Boolean active = true;
    // private Boolean active = true; Esto inicializa el atributo cuando creas el objeto:
    // Room room = new Room();

    private Integer floorNumber;

}
