package com.demo.controller;

import com.demo.model.Room;
import com.demo.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

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

    @GetMapping("salas/{id}")
    public String roomDetail(Model model, @PathVariable Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);

        boolean existe = roomOptional.isPresent();

        if (existe) {
            model.addAttribute("room", roomRepository.findById(id));
            return "rooms/room-detail";
        }else{
            return "rooms/room-list";
        }

    }
}
