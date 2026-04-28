package com.demo.controller;

import com.demo.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;

    @GetMapping("tickets")
    public String getTickets(){
        return ticketRepository.findAll();
    }

    @GetMapping("tickets\{id}")
    public String getTicket(@PathVariable Long id, Model model){
        return ticketRepository.findById(id);
    }

}
