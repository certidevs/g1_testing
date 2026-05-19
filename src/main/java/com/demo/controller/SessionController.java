package com.demo.controller;

    import com.demo.model.Session;
    import com.demo.repository.SessionRepository;
    import com.demo.repository.MovieRepository;
    import com.demo.repository.RoomRepository;
    import com.demo.repository.TicketRepository;
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

        // Lista de sesiones
        @GetMapping("/sessions")
        public String listSessions(Model model) {
            model.addAttribute("proyecciones", sessionRepository.findAll());
            model.addAttribute("proyeccionesCount", sessionRepository.count());
            return "sessions/session-list";
        }

        // Formulario nueva sesión
        @GetMapping("/sessions/new")
        public String newSession(Model model) {
            // TODO cambiar proyecciones a proyeccion porque es solo una

            model.addAttribute("proyeccion", new Session());
            model.addAttribute("movies", movieRepository.findAll()); // Todas las películas
            model.addAttribute("rooms", roomRepository.findAll());   // Todas las salas
            return "sessions/session-form";
        }

        // Detalle de sesión
        @GetMapping("/sessions/{id}")
        public String sessionDetail(Model model, @PathVariable Long id) {
            // TODO cambiar proyecciones a proyección porque es solo una
            model.addAttribute("proyeccion", sessionRepository.findById(id).orElseThrow());
            model.addAttribute("tickets", ticketRepository.findBySession_Id(id)); // Cargar los tickets de esta sesión
            return "sessions/session-detail";
        }

        // Formulario editar sesión
        @GetMapping("/sessions/edit/{id}")
        public String editSession(Model model, @PathVariable Long id) {
            // TODO cambiar proyecciones a proyeccion porque es solo una
            model.addAttribute("proyeccion", sessionRepository.findById(id).orElseThrow());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("rooms", roomRepository.findAll());
            return "sessions/session-form";
        }

        // POST: Guardar (crear o actualizar)
        @PostMapping("/sessions")
        public String saveSession(@ModelAttribute  Session session) {
            sessionRepository.save(session);
            // Guardar la sesión (crea o actualiza)
            boolean isNew = session.getId() == null;
            Session saved = sessionRepository.save(session);

            if (isNew) {
                // Recuperar la sala completa (por si el objeto session solo trae el id)
                Room room = null;
                if (saved.getRoom() != null && saved.getRoom().getId() != null) {
                    room = roomRepository.findById(saved.getRoom().getId()).orElse(null);
                }

                if (room == null) {
                    // No hay sala asociada o no encontrada: no se generan tickets
                } else {
                    Integer capacity = room.getCapacity();
                    if (capacity == null || capacity <= 0) {
                        // Capacidad no válida: no generar tickets
                    } else {
                        // Configuración simple: X butacas por fila
                        final int seatsPerRow = 10; // puedes ajustar o sacar de Room si lo modelas
                        List<Ticket> tickets = new ArrayList<>(capacity);
                        char rowLetter = 'A';
                        int created = 0;

                        while (created < capacity && rowLetter <= 'Z') {
                            for (int seatNum = 1; seatNum <= seatsPerRow && created < capacity; seatNum++) {
                                Ticket t = Ticket.builder()
                                        .session(saved)
                                        .row(String.valueOf(rowLetter))
                                        .seat(String.valueOf(seatNum))
                                        .price(saved.getPrice())
                                        .discount(0.0)
                                        .status(BuyStatus.LIBRE)
                                        .build();
                                tickets.add(t);
                                created++;
                            }
                            rowLetter++;
                        }

                        if (!tickets.isEmpty()) {
                            ticketRepository.saveAll(tickets);
                        }
                    }
                }
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