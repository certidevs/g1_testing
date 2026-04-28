package com.demo.repository;

import com.demo.model.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest

public class SessionRepositoryTest {
    @Autowired
    SessionRepository sessionRepository;

    @BeforeEach
    void setUp(){

    }

    @Test
    void findByMovieId(){

    }

    @Test
    void findByMovieIdAndRoomId(){

    }

    @Test
    void findByLanguage(){

    }

}
