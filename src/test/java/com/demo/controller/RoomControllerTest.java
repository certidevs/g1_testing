package com.demo.controller;

import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import com.demo.repository.RoomRepository;
import com.demo.repository.SessionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

// imports importantes
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest // Activa Spring
@AutoConfigureMockMvc // Activa MockMvc para testing de controller
@Transactional // deshace los cambios al final de cada test para no afectar al siguiente test
class RoomControllerTest {
    // importar mockMVC
    @Autowired MockMvc mockMvc;

    // importar repos
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    SessionRepository sessionRepository;

    Room room;

    @BeforeEach
    void setUp() {
        sessionRepository.deleteAll();
        roomRepository.deleteAll();
        room = roomRepository.save(Room.builder().active(true).name("Sala 1").active(true).screenType(ScreenType.IMAX).capacity(100).build());
    }

    /*@Test
    void list() {
        Assertions.fail("pendiente list");
    }

    @Test
    void detailSala() {
        Assertions.fail("pendiente detail");
    }

    @Test
    void deactivateSala() {
        Assertions.fail("pendiente desactivar");
    }*/

    @Test
    void createSala() throws Exception {
        //count
        long countBefore = roomRepository.count();

        // mockmvc perform post /salas
        mockMvc.perform(post ("/salas")
                .param("name","sala test")
                .param("capacity","50")
                .param("screenType",ScreenType.IMAX.toString())
        ).andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/salas"));

        //count +1
        assertEquals(countBefore +1,roomRepository.count());
        //findById y comprobar datos
        Room room = roomRepository.findAll().getLast();
        assertEquals("sala test", room.getName());

    }

    @Test
    void editSala() throws Exception {
        //Guardar valores originales para comparar
        Long id = room.getId();
        Optional<Room> roomBefore = roomRepository.findById(id);
        assertTrue(roomBefore.isPresent());

        long countBefore = roomRepository.count();

        //Hacer el POST o PUT al endpoint de edición
        mockMvc.perform(post("/salas")
                .param("id",roomBefore.get().getId().toString())
                .param("name","sala testerrr")
                .param("capacity", "200")
                .param("screenType", ScreenType.IMAX.toString())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/salas"));
        //Comprobar que no se creó una nueva sala
        assertEquals(countBefore, roomRepository.count());

        //Buscar la sala editada en el repositorio
        Room roomAfter = roomRepository.findById(room.getId()).orElseThrow();

        assertEquals("sala testerrr", roomAfter.getName());
        assertEquals(200, roomAfter.getCapacity());
        assertEquals(ScreenType.IMAX,roomAfter.getScreenType());
        assertEquals(true, roomAfter.getActive());

    }
}