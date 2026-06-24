package com.demo.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SessionTest {
    @Test
    void builderTest(){
        Session session = Session.builder().id(1L).startTime(LocalDateTime.now().plusDays(1)).language("Español").adMinutes(15).build();
        assertEquals(1L, session.getId());
        assertEquals("Español", session.getLanguage());
        assertEquals(15, session.getAdMinutes());
    }
}
