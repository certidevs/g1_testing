package com.demo.controller;

import com.demo.model.Ticket;
import com.demo.model.User;
import com.demo.model.enums.BuyStatus;
import com.demo.model.enums.Role;
import com.demo.repository.SessionRepository;
import com.demo.repository.TicketRepository;
import com.demo.repository.UserRepository;
import com.demo.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class TicketController {

    //private final TicketRepository ticketRepository;
    private final TicketService ticketService;

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;

    @GetMapping("tickets")
    public String getTickets(Model model, @AuthenticationPrincipal User user){
        List<Ticket> tickets = null;
        if (user.getRole()== Role.ROLE_ADMIN){
            tickets = ticketService.findAll();

        } else if (user.getRole()== Role.ROLE_USER){
            tickets = ticketService.getByUserId(user.getId());

        }
        model.addAttribute("tickets", tickets);
        if (tickets!=null)
            model.addAttribute("numTickets", tickets.size());
        else
            model.addAttribute("numTickets",0);
        model.addAttribute("title", "Listado de tickets");
        return "tickets/ticket-list";
    }

    @GetMapping("tickets/{id}")
    public String ticketDetail(@PathVariable Long id, Model model){
        model.addAttribute("ticket", ticketService.findById(id).orElseThrow());
        return "tickets/ticket-detail";
    }

    @GetMapping("tickets/edit/{id}")
    public String editTicket(@PathVariable Long id, Model model){
        model.addAttribute("ticket", ticketService.findById(id).orElseThrow());
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("sessions",sessionRepository.findAll());
        return"tickets/ticket-form";
    }

    // @PostMapping("tickets/new")

    @GetMapping("tickets/buy/{id}")
    public String buyTicket(@PathVariable Long id, Model model, @AuthenticationPrincipal User user){

        Ticket ticket = ticketService.findById(id).orElseThrow();
        ticket.setBuyDateTime(LocalDateTime.now());
        ticket.setStatus(BuyStatus.PAGADO);

        Double totalPrice = ticket.getSession().getPrice();
        // primero sacar el precio de la sala
        // segundo sumar precio de comida
        // tercero restar descuento
        // LocalDateTime.now().getDayOfWeek() == 3 entonces aplicar descuento
        // descuento por edad de usuario si tenemos su cumpleaños


        if (user!=null){
            ticket.setUser(user);
        }

        ticket.setPrice(totalPrice);
        // TODO sumar precios de comida

        ticketService.save(ticket);
        return "redirect:/tickets/" + ticket.getId();

    }

    @PostMapping("/tickets/{id}/snack")
    public String addSnackToTicket(@PathVariable Long id, @RequestParam Double snackPrice) {
        Ticket ticket = ticketService.findById(id).orElseThrow();

        // Acumular el precio del snack
        Double currentSnackPrice = ticket.getSnackPrice() != null ? ticket.getSnackPrice() : 0.0;
        ticket.setSnackPrice(currentSnackPrice + snackPrice);

        ticketService.save(ticket);
        return "redirect:/tickets/" + id;
    }



}
