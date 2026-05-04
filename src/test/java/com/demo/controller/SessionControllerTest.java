package com.demo.controller;

import com.demo.repository.MovieRepository;
import com.demo.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

public class SessionControllerTest {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        sessionRepository.deleteAll();
    }

    @Test
    void sessionEmpty() throws Exception {
        mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk())
                .andExpect(view().name("sessions/session-list"))
                .andExpect(model().attributeExists("proyecciones"))
                .andExpect(model().attribute("proyecciones", hasSize(0)));
    }

    @Test
    void sessionFull() throws Exception {
        mockMvc.perform(get("/sessions"))
                .andExpect(status().isOk())
                .andExpect(view().name("sessions/session-list"))
                .andExpect(model().attributeExists("proyecciones"))
                .andExpect(model().attribute("proyecciones", hasSize((int) sessionRepository.count())));
    }

    @Test
    void newSession() throws Exception {
        mockMvc.perform(get("/sessions/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("sessions/session-form"))
                .andExpect(model().attributeExists("session"));
    }

    @Test
    void sessionDetail() throws Exception {
        var session = sessionRepository.save(com.demo.model.Session.builder().build());
        mockMvc.perform(get("/sessions/" + session.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("sessions/session-detail"))
                .andExpect(model().attributeExists("proyeccion"))
                .andExpect(model().attribute("proyeccion", hasProperty("id", is(session.getId()))));
    }

}
