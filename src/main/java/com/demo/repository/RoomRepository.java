package com.demo.repository;

import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByScreenType(ScreenType screenType);

    List<Room> findByCapacityGreaterThan(int capacity);
}