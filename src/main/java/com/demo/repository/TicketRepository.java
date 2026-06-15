package com.demo.repository;

import com.demo.model.Ticket;
import com.demo.model.enums.BuyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySession_Id(Long id);

    List<Ticket> findBySession_Movie_Id(Long id);

    List<Ticket> findByUser_Id(Long id);

    List<Ticket> findByStatus(BuyStatus status);

    List<Ticket> findByUser_IdAndStatus(Long userId, BuyStatus status);

    boolean existsByUser_IdAndSession_Movie_IdAndStatus(Long userId, Long movieId, BuyStatus status);

}