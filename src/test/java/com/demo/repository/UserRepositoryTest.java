package com.demo.repository;

import com.demo.model.User;
import com.demo.model.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests para UserRepository")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Debería guardar un usuario y generar su ID")
    void guardarUsuarioTest() {
        User user = crearUsuario(
                "fran",
                "fran@example.com",
                "Fran",
                "Ramírez"
        );

        User guardado = userRepository.save(user);

        assertNotNull(guardado.getId());
        assertEquals("fran", guardado.getUsername());
        assertEquals("fran@example.com", guardado.getEmail());
        assertEquals(Role.ROLE_USER, guardado.getRole());
        assertTrue(guardado.getActive());
    }

    @Test
    @DisplayName("Debería encontrar un usuario por email")
    void findByEmailTest() {
        User user = crearUsuario(
                "adrian",
                "adrian@example.com",
                "Adrián",
                "López"
        );
        userRepository.save(user);

        Optional<User> encontrado = userRepository.findByEmail("adrian@example.com");

        assertTrue(encontrado.isPresent());
        assertEquals("adrian", encontrado.get().getUsername());
        assertEquals("Adrián", encontrado.get().getFirstName());
    }

    @Test
    @DisplayName("Debería devolver Optional vacío si el email no existe")
    void findByEmailNoExistenteTest() {
        Optional<User> encontrado = userRepository.findByEmail("noexiste@example.com");

        assertTrue(encontrado.isEmpty());
    }

    @Test
    @DisplayName("Debería encontrar un usuario por username")
    void findByUsernameTest() {
        User user = crearUsuario(
                "barbara",
                "barbara@example.com",
                "Barbara",
                "Urbano"
        );
        userRepository.save(user);

        Optional<User> encontrado = userRepository.findByUsername("barbara");

        assertTrue(encontrado.isPresent());
        assertEquals("barbara@example.com", encontrado.get().getEmail());
        assertEquals("Urbano", encontrado.get().getLastName());
    }

    @Test
    @DisplayName("Debería comprobar si existe un email")
    void existsByEmailTest() {
        User user = crearUsuario(
                "andres",
                "andres@example.com",
                "Andrés",
                "Soto"
        );
        userRepository.save(user);

        boolean existe = userRepository.existsByEmail("andres@example.com");
        boolean noExiste = userRepository.existsByEmail("otro@example.com");

        assertTrue(existe);
        assertFalse(noExiste);
    }

    @Test
    @DisplayName("Debería comprobar si existe un username")
    void existsByUsernameTest() {
        User user = crearUsuario(
                "cliente1",
                "cliente1@example.com",
                "Cliente",
                "Uno"
        );
        userRepository.save(user);

        boolean existe = userRepository.existsByUsername("cliente1");
        boolean noExiste = userRepository.existsByUsername("cliente2");

        assertTrue(existe);
        assertFalse(noExiste);
    }

    @Test
    @DisplayName("Debería buscar usuarios por apellido ignorando mayúsculas y minúsculas")
    void findByLastNameContainingIgnoreCaseTest() {
        User user1 = crearUsuario(
                "user1",
                "user1@example.com",
                "User",
                "Ramirez"
        );

        User user2 = crearUsuario(
                "user2",
                "user2@example.com",
                "User",
                "ramirez de haro"
        );

        User user3 = crearUsuario(
                "user3",
                "user3@example.com",
                "User",
                "Soto"
        );

        userRepository.saveAll(List.of(user1, user2, user3));

        List<User> encontrados = userRepository.findByLastNameContainingIgnoreCase("RAMIREZ");

        assertEquals(2, encontrados.size());
        assertTrue(encontrados.stream()
                .allMatch(user -> user.getLastName().toLowerCase().contains("ramirez")));
    }

    @Test
    @DisplayName("Debería eliminar todos los usuarios")
    void deleteAllTest() {
        User user1 = crearUsuario(
                "delete1",
                "delete1@example.com",
                "Delete",
                "One"
        );

        User user2 = crearUsuario(
                "delete2",
                "delete2@example.com",
                "Delete",
                "Two"
        );

        userRepository.saveAll(List.of(user1, user2));

        assertEquals(2, userRepository.count());

        userRepository.deleteAll();

        assertEquals(0, userRepository.count());
    }

    private User crearUsuario(String username, String email, String firstName, String lastName) {
        return User.builder()
                .username(username)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .password("password123")
                .role(Role.ROLE_USER)
                .active(true)
                .build();
    }
}
