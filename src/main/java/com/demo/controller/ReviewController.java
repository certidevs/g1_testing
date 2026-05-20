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
    public String review(Model model, @PathVariable Long id) {
        model.addAttribute("review",  reviewRepository.findById(id).orElseThrow());
        return "reviews/review-detail";
    }

    // TODO GetMapping reviews/new

    // TODO GetMapping reviews/edit/{id}

    // TODO PostMapping reviews/new
    @GetMapping("reviews/new")
    public String newReview(
            Model model,
            @RequestParam(required = false) Long movieId) {
        Review review = new Review();

        if (movieId != null)
            review.setMovie(movieRepository.findById(movieId).orElseThrow());

        model.addAttribute("review", review);
        return "reviews/review-form";
    }


    // Get Mapping reviews / edit / {id}
    @GetMapping("reviews/edit/{id}")
    public String editReview(Model model, @PathVariable Long id) {
        model.addAttribute("review", reviewRepository.findById(id).orElseThrow());
        return "reviews/review-form";
    }


    // @PostMapping reviews
    @PostMapping("reviews")
    public String saveReview(@ModelAttribute Review review) {
        reviewRepository.save(review);

        if (review.getMovie() != null)
            return "redirect:/movies/" + review.getMovie().getId();

        return "redirect:/reviews";
    }




    @GetMapping("reviews/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reviewRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Borrado exitosamente");
        return "redirect:/reviews";
    }
}