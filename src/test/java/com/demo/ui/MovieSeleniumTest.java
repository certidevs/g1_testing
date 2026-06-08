package com.demo.ui;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Disabled
public class MovieSeleniumTest extends BaseSeleniumTest{
    @Test
    void movieList(){
        driver.get(baseUrl + "movies");

        //Verificamos el h1 de la página (debemos tener 1 solo h1 por html)
        //el .tagName busca la etiqueta <h1>
        assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Listado de peliculas"));

        //Verificamos el badge de resultados (ej: "2 resultados")
        //El .cssSelector es una clase de css compuesta
        assertTrue(driver.findElement(By.cssSelector(".badge.bg-secondary")).getText().contains("resultado"));

        //Verifica que hay cards de películas
        //Esta buscando por clase de CSS
        List<WebElement> cards = driver.findElements(By.className("card-movie"));
        assertFalse(cards.isEmpty());

        //La primera card tiene el nombre de la película que creamos
        WebElement firstCard = cards.getFirst();
        assertTrue(firstCard.getText().contains(movieAction.getTitle()));

        //Al hacer click en el boton más información estamos verificando que navegamos al movie-detail
        //el linkText busca la etiqueta <a> con el texto exacto que le pasamos
        //el getCurrentUrl es la url actual del navegador
        firstCard.findElement(By.linkText("Más información")).click();
        assertEquals(baseUrl + "movies/" + movieAction.getId(), driver.getCurrentUrl());
    }

    @Test
    void movieDetail() {
        driver.get(baseUrl + "movies/" + movieAction.getId());

        //Estamos comprobando que el título de la película aparece en la card
        //el .cssSelector es la clase compuesta de css
        String titulo = driver.findElement(By.cssSelector(".card-title span")).getText();
        assertEquals(movieAction.getTitle(), titulo);

        //Aqui verificamos que el director aparece en la página de movie-detail
        assertTrue(driver.getPageSource().contains(movieAction.getDirector()));
    }

}
