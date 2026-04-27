package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByMovieId (Long movieId);
    List<Session> findByMovieIdAndRoomId (Long cinemaId);
    List<Session> findByLanguage(Long cinemaId, Long roomId);


}
