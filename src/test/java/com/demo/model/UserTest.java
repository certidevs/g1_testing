package com.demo.model;

import com.demo.model.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias de la entidad User")
class UserTest {

    @Test
    @DisplayName("Debería crear un usuario correctamente con Builder")
    void shouldCreateUserWithBuilder() {
        User user = User.builder()
                .id(1L)
                .username("fran")
                .email("fran@example.com")
                .firstName("Fran")
                .lastName("Ramírez")
                .password("password123")
                .role(Role.ROLE_USER)
                .active(true)
                .imageUrl("/img/fran.png")
                .build();

        assertAll(
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("fran", user.getUsername()),
                () -> assertEquals("fran@example.com", user.getEmail()),
                () -> assertEquals("Fran", user.getFirstName()),
                () -> assertEquals("Ramírez", user.getLastName()),
                () -> assertEquals("password123", user.getPassword()),
                () -> assertEquals(Role.ROLE_USER, user.getRole()),
                () -> assertTrue(user.getActive()),
                () -> assertEquals("/img/fran.png", user.getImageUrl())
        );
    }

    @Test
    @DisplayName("isEnabled debería devolver true solo si active es true")
    void isEnabledShouldDependOnActiveField() {
        User activeUser = User.builder().active(true).build();
        User inactiveUser = User.builder().active(false).build();
        User nullActiveUser = User.builder().active(null).build();

        assertTrue(activeUser.isEnabled());
        assertFalse(inactiveUser.isEnabled());
        assertFalse(nullActiveUser.isEnabled());
    }

    @Test
    @DisplayName("getAuthorities debería devolver el rol del usuario")
    void getAuthoritiesShouldReturnUserRole() {
        User user = User.builder()
                .role(Role.ROLE_ADMIN)
                .build();

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        assertEquals(1, authorities.size());
        assertTrue(authorities.stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    @DisplayName("toString no debería incluir la contraseña")
    void toStringShouldNotContainPassword() {
        User user = User.builder()
                .username("fran")
                .password("superSecret123")
                .build();

        String result = user.toString();

        assertTrue(result.contains("username=fran"));
        assertFalse(result.contains("superSecret123"));
        assertFalse(result.contains("password="));
    }
}
