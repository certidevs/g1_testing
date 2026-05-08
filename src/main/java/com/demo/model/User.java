package com.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "password") // Seguridad: No mostrar el password en logs
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(name = "first_name", length = 50)
    private String firstName; // Corregido 'fistName'

    @Column(name = "last_name", length = 50)
    private String lastName; // Corregido 'lastname' a CamelCase

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Column(nullable = false)
    private String password;


}