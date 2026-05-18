package com.demo.repository;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Fundamental para el proceso de autenticación/login
    Optional<User> findByEmail(String email);

    // Búsqueda para listados o administración
    List<User> findByLastNameContainingIgnoreCase(String lastName);

    // Verificar si un email ya existe antes de registrar
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}