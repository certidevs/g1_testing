package com.demo.controller;

import com.demo.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class RoomController {

    //repositorio de room (salas)
    private final RoomRepository roomRepository;

    @GetMapping("salas")
    public String rooms(Model model) {
        //model donde se cargan los datos
        model.addAttribute("rooms", roomRepository.findAll());
        return "rooms/room-list";
    }
}
