package com.demo.service;

import com.demo.model.Ticket;
import com.demo.model.Session;
import com.demo.model.Room;
import com.demo.model.User;
import com.demo.model.enums.BuyStatus;
import com.demo.repository.TicketRepository;
import com.demo.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final RoomRepository roomRepository;

    public List <Ticket> getBySessionId(Long id){
        return ticketRepository.findBySession_Id(id);
    }
    public List <Ticket> getByMovieId(Long id){
        return ticketRepository.findBySession_Movie_Id(id);
    }
    public List <Ticket> getByUserId(Long id){
        return ticketRepository.findByUser_Id(id);
    }
    public List <Ticket> findAll(){
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id){
        return ticketRepository.findById(id);
    }

    public Ticket save(Ticket ticket){
        return ticketRepository.save(ticket);
    }

    public List <Ticket> generarTickets(Session session) {

        // Validar que session tenga una sala asociada
        if (session.getRoom() == null || session.getRoom().getId() == null) {
            return List.of();
        }

        // Recuperar el id de la room asociada a la sesion

        Room room = roomRepository.findById(session.getRoom().getId()).orElseThrow();

        Integer capacity = room.getCapacity();

        if (capacity == null || capacity <=0){
            return List.of();
        }

        //int seatsPerRow = 15;
        int seatsPerRow = (int) Math.ceil(Math.sqrt(capacity));

        List<Ticket> tickets = new ArrayList<>(capacity);
        char rowLetter = 'A';
        int created = 0;

        while (created < capacity && rowLetter <= 'Z') {
            for (int seatNum = 1; seatNum <= seatsPerRow && created < capacity; seatNum++) {

                Ticket ticket = Ticket.builder()
                        .session(session)
                        .row(String.valueOf(rowLetter))
                        .seat(String.valueOf(seatNum))
                        .price(session.getPrice())
                        .discount(0.0)
                        .status(BuyStatus.LIBRE)
                        .build();

                tickets.add(ticket);
                created++;
            }
            rowLetter++;
        }

        // Guardar datos
        if (!tickets.isEmpty()) {
            ticketRepository.saveAll(tickets);
        }
        return tickets;
    }
}