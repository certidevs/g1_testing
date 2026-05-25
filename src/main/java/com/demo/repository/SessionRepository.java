package com.demo.repository;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.model.Session;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByMovie_Id (Long id);
    List<Session> findByMovieIdAndRoomId (Long movieId, Long roomId);
    List<Session> findByLanguage(String language);
    List<Session> findByAdMinutesLessThanEqual(int i);


    // TODO query order by startTIme desc para listado proyecciones admin
}
