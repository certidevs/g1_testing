package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByMovie_Id (Long id);
    List<Session> findByMovieIdAndRoomId (Long movieId, Long roomId);
    List<Session> findByLanguage(String language);
}
