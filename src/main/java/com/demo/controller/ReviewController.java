package com.demo.controller;

import com.demo.model.Review;
import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.repository.MovieRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class ReviewController {

    // inyectar el repositorio de reviews
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    // getmapping reviews
    @GetMapping("/reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/review-list";
    }

    @GetMapping("/reviews/{id}")
    public String reviewDetail(Model model, @PathVariable Long id,
                               @RequestParam(required = false) String returnUrl) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        // Seguridad -> solo aceptamos rutas internas (empiezan por /)
        String backUrl = (returnUrl != null && returnUrl.startsWith("/")) ? returnUrl : "/reviews";
        model.addAttribute("returnUrl", backUrl);
        return "reviews/review-detail";
    }

    @GetMapping("/reviews/new")
    public String newReview(Model model, @RequestParam(required = false) Long movieId) {
        Review review = new Review();
        if (movieId != null)
            review.setMovie(movieRepository.findById(movieId).orElseThrow());
        model.addAttribute("review", review);
        return "reviews/review-form";
    }

    @GetMapping("/reviews/edit/{id}")
    public String editReview(Model model, @PathVariable Long id,
                             @AuthenticationPrincipal User userActual) {
        Review review = reviewRepository.findById(id).orElseThrow();

        // Solo el autor o un admin puede editar
        boolean isAdmin = userActual.getRole().equals(Role.ROLE_ADMIN);
        boolean isOwner = review.getUser() != null
                && review.getUser().getUsername().equals(userActual.getUsername());

        if (!isAdmin && !isOwner)
            return "redirect:/reviews/" + id;

        model.addAttribute("review", review);
        return "reviews/review-form";
    }

    //movieId viene del campo hidden del formulario para asociar la película correctamente
    @PostMapping("/reviews")
    public String saveReview(@ModelAttribute Review review,
                             @RequestParam(required = false) Long movieId,
                             @AuthenticationPrincipal User currentUser) {

        //System.out.println("CURRENT USER: " + currentUser);
        if (movieId != null)
            review.setMovie(movieRepository.findById(movieId).orElse(null));

        if (review.getId() != null) {
            // Edición: conservar el autor original, no sobreescribirlo
            reviewRepository.findById(review.getId())
                    .ifPresent(existing -> review.setUser(existing.getUser()));
        } else {
            // Nueva reseña: asignar el usuario autenticado como autor
            review.setUser(currentUser);
        }

        reviewRepository.save(review);

        if (review.getMovie() != null)
            return "redirect:/movies/" + review.getMovie().getId();

        return "redirect:/reviews";
    }

    @GetMapping("/reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Solo ADMIN pasa por aqui, comprobado con debug, SecurityConfig ya lo garantiza a nivel de ruta
        reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Reseña eliminada correctamente.");
        return "redirect:/reviews";
    }
}