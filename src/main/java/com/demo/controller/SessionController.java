package com.demo.controller;

import com.demo.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor

public class SessionController {
    //Inyectar el repositorio de Session
    private SessionRepository sessionRepository;

    //GetMapping de Session
    @GetMapping("sessions")
    public String Session(Model model) { return ""; }
}
