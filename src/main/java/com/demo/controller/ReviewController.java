package com.demo.controller;

import com.demo.model.Review;
import com.demo.repository.MovieRepository;
import com.demo.repository.ReviewRepository;
import lombok.AllArgsConstructor;
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
    @GetMapping("reviews")
    public String reviews(Model model) {
        model.addAttribute("reviews", reviewRepository.findAll());
        return "reviews/review-list";
    }

    @GetMapping("reviews/{id}")
    public String reviewDetail(Model model, @PathVariable Long id) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/review-detail";
    }

    @GetMapping("reviews/new")
    public String newReview(Model model, @RequestParam(required = false) Long movieId) {
        Review review = new Review();
        if (movieId != null)
            review.setMovie(movieRepository.findById(movieId).orElseThrow());
        model.addAttribute("review", review);
        return "reviews/review-form";
    }

    @GetMapping("reviews/edit/{id}")
    public String editReview(Model model, @PathVariable Long id) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/review-form";
    }

    //movieId viene del campo hidden del formulario para asociar la película correctamente
    @PostMapping("reviews")
    public String saveReview(@ModelAttribute Review review,
                             @RequestParam(required = false) Long movieId) {
        if (movieId != null)
            review.setMovie(movieRepository.findById(movieId).orElse(null));

        reviewRepository.save(review);

        if (review.getMovie() != null)
            return "redirect:/movies/" + review.getMovie().getId();

        return "redirect:/reviews";
    }

    @GetMapping("reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Reseña eliminada correctamente.");
        return "redirect:/reviews";
    }
}