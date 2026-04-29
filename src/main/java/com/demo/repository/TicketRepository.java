package com.demo.repository;

import com.demo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findBySession_Id(Long id);

    List<Ticket> findBySession_Movie_Id(Long id);

    List<Ticket> findByUser_Id(Long id);


}