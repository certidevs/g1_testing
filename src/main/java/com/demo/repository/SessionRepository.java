package com.demo.repository;

import org.jspecify.annotations.Nullable;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.demo.model.Session;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByMovie_Id (Long id);
    List<Session> findByMovieIdAndRoomId (Long movieId, Long roomId);
    List<Session> findByLanguage(String language);
    List<Session> findByAdMinutesLessThanEqual(int i);
    List<Session> findAllByOrderByStartTimeDesc();

    @Query("SELECT s FROM Session s ORDER BY s.startTime DESC NULLS LAST")
    List<Session> findAllOrderByStartTimeDescNullsLast();
}