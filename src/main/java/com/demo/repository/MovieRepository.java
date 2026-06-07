package com.demo.repository;

import com.demo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByActive(Boolean active);


    //Peliculas entre X min e Y max en orden descendente (entonces trae los de menos duracion)
    List<Movie> findByDurationMinutesBetween(Integer durationMinutesStart, Integer durationMinutesEnd);

    List<Movie> findByGenre(String genre);

    //Se puede poner un filtro que sea peliculas de MENOS de X minutos (menos de 1h y media = 90min, 2hs = 120 min), 2h y media = 150min, 3hs = 180min

    @Query("""
        SELECT m FROM Movie m
        WHERE m.active = true
        AND(:durationMinutes IS NULL OR m.durationMinutes <= :durationMinutes)
        AND(:genre IS NULL OR m.genre = :genre)
        AND(:releaseYear IS NULL OR m.releaseYear >= :releaseYear)
        AND (:title IS NULL OR :title = '' OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%')))
                """)

    List<Movie> findActiveFiltering(
            @Param("durationMinutes") Integer durationMinutes,
            @Param("genre") String genre,
            @Param("releaseYear") Integer releaseYear,
            @Param("title") String title
    );

    List<Movie> findByDurationMinutesLessThanEqual(Integer durationMinutes);

    List<Movie> findByDirector(String director);


}
