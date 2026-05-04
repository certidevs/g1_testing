package com.demo.controller;

import com.demo.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor

public class SessionController {
    //Inyectar el repositorio de Session
    private SessionRepository sessionRepository;

    //GetMapping de Session
    @GetMapping("sessions")
    public String Session(Model model) {
        model.addAttribute("proyecciones", sessionRepository.findAll());
        model.addAttribute("proyeccionesCount", sessionRepository.count());
        return "sessions/session-list"; }

    @GetMapping("sessions/new")
    public String newSession(Model model) {
        model.addAttribute("session", new com.demo.model.Session());
        return "sessions/session-form";}

    @GetMapping("sessions/{id}")
    public String sessionDetail(Model model, @PathVariable Long id) {
        model.addAttribute("proyeccion", sessionRepository.findById(id).orElseThrow());
        return "sessions/session-detail";}

    }