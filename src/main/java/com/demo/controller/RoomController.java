package com.demo.controller;

import com.demo.model.Room;
import com.demo.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RoomController {

    //repositorio de room (salas)
    private final RoomRepository roomRepository;

    @GetMapping("salas")
    public String rooms(Model model) {
        //model donde se cargan los datos
        model.addAttribute("rooms", roomRepository.findAllByActiveTrue());
        return "rooms/room-list";
    }

    @GetMapping("salas/{id}")
    public String roomDetail(Model model, @PathVariable Long id) {
        Optional<Room> roomOptional = roomRepository.findByIdAndActiveTrue(id);

        boolean existe = roomOptional.isPresent();

        if (existe) {
            Room room = roomOptional.get();
            model.addAttribute("room", room);
            return "rooms/room-detail";
        }else{
            return "rooms/room-list";
        }

    }

    @GetMapping("salas/deactivate/{id}")
    public String deactivateSala(@PathVariable Long id, Model model) {

        // forma 1:
//        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
//        if (restaurantOptional.isPresent()) {
//            Restaurant restaurant = restaurantOptional.get();
//            restaurant.setActive(false);
//            restaurantRepository.save(restaurant);
//        }
//        return "redirect:/restaurants";

        // forma 2 (opcional):
        roomRepository.findById(id).ifPresent(room -> {
            room.setActive(false);
            roomRepository.save(room);
        });
        return "redirect:/salas";
    }
}
