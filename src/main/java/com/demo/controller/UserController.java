package com.demo.controller;


import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.service.FileService;
import com.demo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            @Valid @ModelAttribute User user,
            BindingResult bindingResult,
            @AuthenticationPrincipal User currentUser,
            RedirectAttributes redirectAttributes,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("roles", Role.values());
            model.addAttribute("edit", user.getId() != null);
            return "users/user-form"; //vuelve al form SIN guardar.
        }

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

    @GetMapping("admin/users/deactivate/{id}")
    public String deactivate(
            @PathVariable Long id,
            @AuthenticationPrincipal User currentUser,
            RedirectAttributes redirectAttributes) {
        try {
            userService.deactivate(id, currentUser.getId());
            redirectAttributes.addFlashAttribute("message", "Usuario desactivado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @GetMapping("admin/users/activate/{id}")
    public String activate(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            userService.activate(id);
            redirectAttributes.addFlashAttribute("message", "Usuario activado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/users";
    }

    @GetMapping("profile")
    public String profile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("user", userService.findById(user.getId()));
        model.addAttribute("userStats", userService.findStatsById(user.getId()));
        return "users/user-detail";
    }

    @GetMapping("profile/edit")
    public String editProfile(Model model, @AuthenticationPrincipal User user) {
        User saved = userService.findById(user.getId());
        saved.setPassword(null);
        model.addAttribute("user", saved);
        return "users/profile-form";
    }

    //En progreso PROFILE POSTMAPPING

    @PostMapping("profile")
    public String saveProfile(@ModelAttribute User userForm,
                              RedirectAttributes ra,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              @AuthenticationPrincipal User authenticatedUser,
                              HttpServletRequest request,
                              HttpServletResponse response) {

        if (authenticatedUser == null || authenticatedUser.getId() == null) {
            log.error("Error usuario {} intentando editar otro usuario {}", authenticatedUser, userForm);
            ra.addFlashAttribute("error", "No tienes permisos");
            return "redirect:/profile";
        }
        // evitar que el usuario pueda cambiar su id, rol, active para evitar escalada de privilegios
        userForm.setId(authenticatedUser.getId());
        userForm.setRole(authenticatedUser.getRole());
        userForm.setActive(authenticatedUser.getActive());

        // imagen
        String imageUrl = fileService.store(imageFile);
        userForm.setImageUrl(imageUrl != null ? imageUrl : authenticatedUser.getImageUrl());

        User userUpdated = userService .update(userForm, authenticatedUser.getId());

        // refrescar Spring Security para que el principal/navbar muestren los datos nuevos
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                userUpdated,
                userUpdated.getPassword(),
                userUpdated.getAuthorities()
        );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(newAuth);

        SecurityContextHolder.setContext(context);

        new HttpSessionSecurityContextRepository().saveContext(context, request, response);

        ra.addFlashAttribute("message", "usuario actualizado");
        return  "redirect:/profile";
    }

}
