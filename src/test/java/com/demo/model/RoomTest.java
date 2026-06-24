package com.demo.model;

import com.demo.model.enums.ScreenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void shouldCreateRoomWithNoArgsConstructor() {
        Room room = new Room();

        assertNull(room.getId());
        assertNull(room.getName());
        assertNull(room.getCapacity());
        assertNull(room.getFloorNumber());
        assertEquals(ScreenType.STANDARD, room.getScreenType());
        assertTrue(room.getActive());
    }

    @Test
    void shouldCreateRoomUsingBuilder() {
        Room room = Room.builder()
                .id(1L)
                .name("Sala IMAX")
                .capacity(150)
                .floorNumber(2)
                .screenType(ScreenType.IMAX)
                .active(true)
                .build();

        assertEquals(1L, room.getId());
        assertEquals("Sala IMAX", room.getName());
        assertEquals(150, room.getCapacity());
        assertEquals(2, room.getFloorNumber());
        assertEquals(ScreenType.IMAX, room.getScreenType());
        assertTrue(room.getActive());
    }

    @Test
    void shouldUpdateRoomUsingSetters() {
        Room room = new Room();

        room.setId(2L);
        room.setName("Sala 4DX");
        room.setCapacity(100);
        room.setFloorNumber(1);
        room.setScreenType(ScreenType.D4X);
        room.setActive(false);

        assertEquals(2L, room.getId());
        assertEquals("Sala 4DX", room.getName());
        assertEquals(100, room.getCapacity());
        assertEquals(1, room.getFloorNumber());
        assertEquals(ScreenType.D4X, room.getScreenType());
        assertFalse(room.getActive());
    }

    @Test
    void shouldAllowDifferentScreenTypes() {
        Room standardRoom = Room.builder()
                .screenType(ScreenType.STANDARD)
                .build();

        Room imaxRoom = Room.builder()
                .screenType(ScreenType.IMAX)
                .build();

        Room d3Room = Room.builder()
                .screenType(ScreenType.D3)
                .build();

        Room d4xRoom = Room.builder()
                .screenType(ScreenType.D4X)
                .build();

        assertEquals(ScreenType.STANDARD, standardRoom.getScreenType());
        assertEquals(ScreenType.IMAX, imaxRoom.getScreenType());
        assertEquals(ScreenType.D3, d3Room.getScreenType());
        assertEquals(ScreenType.D4X, d4xRoom.getScreenType());
    }

    @Test
    void shouldStoreRoomActiveStatus() {
        Room room = new Room();

        room.setActive(true);
        assertTrue(room.getActive());

        room.setActive(false);
        assertFalse(room.getActive());
    }
}
