package com.demo.controller;

import com.demo.repository.MovieRepository;
import com.demo.repository.SessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc

public class SessionControllerTest {
    @Autowired
    SessionRepository sessionRepository;

    @Test
    void sessionEmpty(){
        //mockMvc.perform();
    }
}
