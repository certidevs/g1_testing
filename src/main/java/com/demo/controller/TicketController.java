package com.demo.controller;

import com.demo.model.Ticket;
import com.demo.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class TicketController {

    private final TicketRepository ticketRepository;

    @GetMapping("tickets")
    public String getTickets(Model model){
        List<Ticket> tickets = ticketRepository.findAll();
        model.addAttribute("tickets", tickets);
        model.addAttribute("numTickets", tickets.size());
        model.addAttribute("title", "Listado de tickets");
        return "tickets/ticket-list";
    }

    @GetMapping("tickets/{id}")
    public String ticketDetail(@PathVariable Long id, Model model){
        model.addAttribute("ticket", ticketRepository.findById(id).orElseThrow());
        return "tickets/ticket-detail";
    }

}
