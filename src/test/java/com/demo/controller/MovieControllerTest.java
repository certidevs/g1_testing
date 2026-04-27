package com.demo.controller;


import com.demo.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {
    @Autowired //Se agrega una anotacion por cada repository que necesitemos aqui
    MovieRepository movieRepository;

    @Test
    void movieEmpty(){
        //mockMvc.perform();
    }

}
