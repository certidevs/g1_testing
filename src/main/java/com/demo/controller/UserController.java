package com.demo.controller;


import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.service.FileService;
import com.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@AllArgsConstructor
@Controller
public class UserController {
    private UserService userService;
    private FileService fileService;

    @GetMapping("admin/users")
    public String list(Model model){
        model.addAttribute("users", userService.findAll());
        return "users/user-list";
    }
    //Para acceder a un usuario en particular, user-detail
    @GetMapping("admin/users/{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("user", userService.findById(id));
//        TODO se debe temrinar con REVIEW para avanzar con esto
// model.addAttribute("userStats", userService.findStatsById(id));
        return "users/user-detail";
    }

    //GetMapping admin/users/new
    @GetMapping("admin/users/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", false);

        return "users/user-form";
    }



    //En progreso PROFILE GETMAPPING
    //TODO terminar con REVIEW para avanzar con est0
//    @GetMapping("profile")
//    public String profile(Model model, @AuthenticationPrincipal User user) {
//        model.addAttribute("user", userService.findById(user.getId()));
//        model.addAttribute("userStats", userService.findStatsById(user.getId()));
//
//        return "users/user-detail";
//    }

    //En progreso PROFILE POSTMAPPING

}
