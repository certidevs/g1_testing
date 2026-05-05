package com.demo.repository;

import com.demo.model.Room;
import com.demo.model.enums.ScreenType;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByScreenType(ScreenType screenType);

    List<Room> findByCapacityGreaterThan(int capacity);

    List<Room> findAllByActiveTrue();

    Optional<Room> findByIdAndActiveTrue(Long id);

    List <Room> findAllByIdAndActiveTrue(Long id);
}