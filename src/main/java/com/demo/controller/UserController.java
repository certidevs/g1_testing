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
        model.addAttribute("userStats", userService.findStatsById(id));
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

    //GetMapping admin/users/edit/{id}
    @GetMapping("admin/users/edit/{id}")
    public String editUser(
            Model model,
            @PathVariable Long id){

        User user = userService.findById(id);
        user.setPassword(null); //Se setea en null para no exponerla

        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("edit", true);

        return "users/user-form";
    }
    //PostMapping admin/users
    @PostMapping("admin/users")
    public String save(
            @ModelAttribute User user,
            @AuthenticationPrincipal User currentUser,
            RedirectAttributes redirectAttributes,
            @RequestParam("imageFile") MultipartFile imageFile
    ){
        //Lo bueno de los logs es que que nos indica la fecha exacta en la que se crea el usuario
        // el PID y dónde esta ocurriendo esto "UserController" y linea de codigo
        //Debe estar la anotacion @Slf4j
        log.info("Guardando user {}", user.getUsername());

        String imageUrl = fileService.store(imageFile);

        if(imageUrl != null){
            user.setImageUrl(imageUrl);
        }
        try{
            if (user.getId() == null){
                user = userService.create(user);
                redirectAttributes.addFlashAttribute("message", "Usuario creado correctamente");
                log.info("Usuario creado correctamente {}", user);
            } else {
                user = userService.update(user, currentUser.getId());
                redirectAttributes.addFlashAttribute("message", "Usuario actualizado correctamente");
                log.info("Usuario actualizado correctamente {}", user);
            }
            //Tanto para CREAR como ACTUALIZAR lo redirecciona a los users
            return "redirect:/admin/users";
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al crear el usuario");
            log.error("Error al crear el usuario {}", e.getMessage());

            return user.getId() == null ?
                    "redirect:/admin/users/new" : "redirect:/admin/users/edit/" + user.getId();
        }
    }

    @GetMapping("profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", userService.findById(user.getId()));
        model.addAttribute("userStats", userService.findStatsById(user.getId()));
        return "users/user-detail";
    }

    //En progreso PROFILE POSTMAPPING

}
