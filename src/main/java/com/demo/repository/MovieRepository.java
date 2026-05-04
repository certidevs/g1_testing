package com.demo.repository;

import com.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //Peliculas entre X min e Y max en orden descendente (entonces trae los de menos duracion)
    List<Movie> findByDurationMinutesBetween(Integer durationMinutesStart, Integer durationMinutesEnd);

    List<Movie> findByGenre(String genre);

    //Se puede poner un filtro que sea peliculas de MENOS de X minutos (menos de 1h y media = 90min, 2hs = 120 min), 2h y media = 150min, 3hs = 180min
    List<Movie> findByDurationMinutesLessThanEqual(Integer durationMinutes);

    List<Movie> findByDirector(String director);


}
