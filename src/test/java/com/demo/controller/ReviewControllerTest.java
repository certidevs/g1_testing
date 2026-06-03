package com.demo.controller;


import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

// TODO
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class ReviewControllerTest {
}
