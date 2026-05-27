package com.demo.controller;

    import com.demo.model.Session;
    import com.demo.repository.SessionRepository;
    import com.demo.repository.MovieRepository;
    import com.demo.repository.RoomRepository;
    import com.demo.repository.TicketRepository;
    import com.demo.service.TicketService;
    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.ModelAttribute;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;

    import com.demo.model.Ticket;
    import com.demo.model.Room;
    import com.demo.model.enums.BuyStatus;
    import java.util.ArrayList;
    import java.util.List;

@Controller
    @AllArgsConstructor
    public class SessionController {

        private final SessionRepository sessionRepository;
        private final MovieRepository movieRepository;
        private final RoomRepository roomRepository;
        private final TicketRepository ticketRepository;
        private final TicketService ticketService;

        // Lista de sesiones
        @GetMapping("/sessions")
public String listSessions(Model model) {
    model.addAttribute("proyecciones", sessionRepository.findAllOrderByStartTimeDescNullsLast());
    model.addAttribute("proyeccionesCount", sessionRepository.count());
    return "sessions/session-list";
}
    // Formulario nueva sesión
    @GetMapping("/sessions/new")
    public String newSession(Model model) {
        model.addAttribute("proyeccion", new Session());
        model.addAttribute("movies", movieRepository.findAll()); // Todas las películas
        model.addAttribute("rooms", roomRepository.findAll()); // Todas las salas
        return "sessions/session-form";
    }

    // Detalle de sesión
    @GetMapping("/sessions/{id}")
    public String sessionDetail(Model model, @PathVariable Long id) {
        int capacity = sessionRepository.findById(id).orElseThrow().getRoom().getCapacity();
        int seatsPerRow = (int) Math.ceil(Math.sqrt(capacity));
        model.addAttribute("proyeccion", sessionRepository.findById(id).orElseThrow());
        model.addAttribute("tickets", ticketRepository.findBySession_Id(id)); // Cargar los tickets de esta sesión
        model.addAttribute("seatsPerRow", seatsPerRow);
        return "sessions/session-detail";
    }

    // Formulario editar sesión
    @GetMapping("/sessions/edit/{id}")
    public String editSession(Model model, @PathVariable Long id) {
        model.addAttribute("proyeccion", sessionRepository.findById(id).orElseThrow());
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("rooms", roomRepository.findAll());
        return "sessions/session-form";
    }

    // POST: Guardar (crear o actualizar)
    @PostMapping("/sessions")
    public String saveSession(@ModelAttribute Session session) {
        // Guardar la sesión (crea o actualiza)
        boolean isNew = session.getId() == null;
        Session saved = sessionRepository.save(session);

        if (isNew) {
            ticketService.generarTickets(saved); // ← NUEVO: Llamar al servicio
        }
        return "redirect:/sessions";
    }

    // GET: Eliminar
    @GetMapping("/sessions/delete/{id}")
    public String deleteSession(@PathVariable Long id) {
        sessionRepository.deleteById(id);
        return "redirect:/sessions";
    }
}