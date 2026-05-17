package com.demo.controller;

    import com.demo.model.Session;
    import com.demo.repository.SessionRepository;
    import com.demo.repository.MovieRepository;
    import com.demo.repository.RoomRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;

    @Controller
    @AllArgsConstructor
    public class SessionController {

        private final SessionRepository sessionRepository;
        private final MovieRepository movieRepository;
        private final RoomRepository roomRepository;

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
            model.addAttribute("proyecciones", new Session());
            model.addAttribute("movies", movieRepository.findAll()); // Todas las películas
            model.addAttribute("rooms", roomRepository.findAll());   // Todas las salas
            return "sessions/session-form";
        }

        // Detalle de sesión
        @GetMapping("/sessions/{id}")
        public String sessionDetail(Model model, @PathVariable Long id) {
            model.addAttribute("proyecciones", sessionRepository.findById(id).orElseThrow());
            return "sessions/session-detail";
        }

        // Formulario editar sesión
        @GetMapping("/sessions/edit/{id}")
        public String editSession(Model model, @PathVariable Long id) {
            model.addAttribute("proyecciones", sessionRepository.findById(id).orElseThrow());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("rooms", roomRepository.findAll());
            return "sessions/session-form";
        }

        // POST: Guardar (crear o actualizar)
        @PostMapping("/sessions")
        public String saveSession(Session proyecciones) {
            sessionRepository.save(proyecciones);
            return "redirect:/sessions";
        }

        // GET: Eliminar
        @GetMapping("/sessions/delete/{id}")
        public String deleteSession(@PathVariable Long id) {
            sessionRepository.deleteById(id);
            return "redirect:/sessions";
        }
    }