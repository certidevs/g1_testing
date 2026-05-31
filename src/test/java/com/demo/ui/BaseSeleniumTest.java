package com.demo.ui;

import com.demo.model.Movie;
import com.demo.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

//Ponemos un puerto aleatorio para no tener problema con el puerto 8080 que estamos utilizando porque podria fallar el test
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseSeleniumTest {
    @LocalServerPort
    int port; //Inyectamos el puerto aleatorio

    @Autowired
    MovieRepository movieRepository;

    String baseUrl;
    WebDriver driver;

    Movie movieAction;
    Movie movieRomance;

    @BeforeEach
    void setUp() {
        movieRepository.deleteAll();
        movieAction = movieRepository.save(
                Movie.builder().active(true).title("The Bourne Identity").genre("Action")
                        .durationMinutes(119).releaseYear(2002).director("Doug Liman").build()
        );
        movieRomance = movieRepository.save(
                Movie.builder().active(true).title("The Age of Adaline").genre("Romance")
                        .durationMinutes(112).releaseYear(2015).director("Lee Toland Krieger").build()
        );

        baseUrl = "http://localhost:" + port + "/";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //Siempre cerramos para que no queden procesos abiertos
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

