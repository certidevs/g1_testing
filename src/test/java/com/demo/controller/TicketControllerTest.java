package com.demo.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class TicketControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("GET /tickets")
    void getTickets() {
    }

    @Test
    @DisplayName("GET /tickets/{id}")
    void ticketDetail() {
    }

    @Test
    @DisplayName("POST /tickets/{id}/edit")
    void editTicket() {
    }
}