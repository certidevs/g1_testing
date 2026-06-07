package com.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


@Data
public class RegisterForm {
    @NotBlank(message = "El username es obligatorio")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email invalido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "Debes repetir la contraseña")
    private String passwordConfirm;
//     private Boolean acceptRGPD;
}