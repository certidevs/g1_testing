package com.demo.repository;

import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//filtro el acceso a los datos
@DataJpaTest
class RoomRepositoryTest {

    @Autowired
    RoomRepository roomRepository;

    @BeforeEach
    void setUp() {
        roomRepository.saveAll(
                List.of(
                        Room.builder().screenType(ScreenType.D4X).capacity(100).build(),
                        Room.builder().screenType(ScreenType.IMAX).capacity(150).build(),
                        Room.builder().screenType(ScreenType.D4X).capacity(80).build(),
                        Room.builder().screenType(ScreenType.IMAX).capacity(120).build(),
                        Room.builder().screenType(ScreenType.D4X).capacity(90).build()
                ));
    }

    //buscar cuantas salas existen
    //findByNumberRooms
    @Test
    void findByNumberRooms() {
        List<Room> rooms = roomRepository.findAll();
        assertEquals(5, rooms.size());

    }

    //buscar por tipo de pantalla
    //findByScreenType
    @Test
    void findByScreenType() {
        List<Room> rooms = roomRepository.findByScreenType(ScreenType.D4X);
        assertEquals(3, rooms.size());
    }


    //buscar por capacidad de la sala
    //findByCapacidadGreaterThan
    @Test
    void findByCapacidadGreaterThan(){
        List<Room> rooms= roomRepository.findByCapacityGreaterThan(100);
        assertEquals(2, rooms.size());
    }


    @AfterEach
    void tearDown() {
        roomRepository.deleteAll();
    }
}