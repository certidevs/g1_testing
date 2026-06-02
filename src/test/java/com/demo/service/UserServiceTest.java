package com.demo.service;

import com.demo.dto.RegisterForm;
import com.demo.model.User;
import com.demo.model.enums.Role;
import com.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private RegisterForm defaultForm;

    @BeforeEach
    public void setUp() {
        defaultForm = new RegisterForm();
        defaultForm.setUsername("john");
        defaultForm.setEmail("john@example.com");
        defaultForm.setPassword("secret123");
        defaultForm.setPasswordConfirm("secret123");
    }

    @Test
    @DisplayName("register: datos válidos -> guarda usuario con contraseña encriptada")
    public void register_withValidData_savesUserWithEncodedPassword() {
        // Arrange
        RegisterForm form = defaultForm;

        when(userRepository.existsByUsername(form.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(form.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(form.getPassword())).thenReturn("ENCODED_secret123");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User saved = userService.register(form);

        // Assert
        assertNotNull(saved);
        assertEquals("john", saved.getUsername());
        assertEquals("john@example.com", saved.getEmail());
        assertEquals(Role.ROLE_USER, saved.getRole());
        assertEquals("ENCODED_secret123", saved.getPassword());

        // Verify interactions
        verify(userRepository, times(1)).existsByUsername("john");
        verify(userRepository, times(1)).existsByEmail("john@example.com");
        verify(passwordEncoder, times(1)).encode("secret123");
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(captor.capture());
        User passedToSave = captor.getValue();
        assertEquals("john", passedToSave.getUsername());
        assertEquals("ENCODED_secret123", passedToSave.getPassword());

        verifyNoMoreInteractions(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("register: username ya existente -> lanza IllegalArgumentException")
    public void register_withExistingUsername_throwsException() {
        // Arrange
        RegisterForm form = new RegisterForm();
        form.setUsername("exists");
        form.setEmail("exists@example.com");
        form.setPassword("pwd");
        form.setPasswordConfirm("pwd");

        when(userRepository.existsByUsername("exists")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> userService.register(form));
        assertTrue(ex.getMessage().toLowerCase().contains("username") || ex.getMessage().toLowerCase().contains("ya existe"));

        verify(userRepository, times(1)).existsByUsername("exists");
        verify(userRepository, never()).existsByEmail(anyString());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
        verifyNoMoreInteractions(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("register: email ya existente -> lanza IllegalArgumentException")
    public void register_withExistingEmail_throwsException() {
        // Arrange
        RegisterForm form = defaultForm;
        when(userRepository.existsByUsername(form.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(form.getEmail())).thenReturn(true);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> userService.register(form));
        assertTrue(ex.getMessage().toLowerCase().contains("email") || ex.getMessage().toLowerCase().contains("ya existe"));

        verify(userRepository, times(1)).existsByUsername(form.getUsername());
        verify(userRepository, times(1)).existsByEmail(form.getEmail());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
        verifyNoMoreInteractions(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("register: contraseñas no coinciden -> lanza IllegalArgumentException")
    public void register_withPasswordMismatch_throwsException() {
        // Arrange
        RegisterForm form = new RegisterForm();
        form.setUsername("mark");
        form.setEmail("mark@example.com");
        form.setPassword("a");
        form.setPasswordConfirm("b");

        when(userRepository.existsByUsername("mark")).thenReturn(false);
        when(userRepository.existsByEmail("mark@example.com")).thenReturn(false);

        // Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> userService.register(form));
        assertTrue(ex.getMessage().toLowerCase().contains("contrase") || ex.getMessage().toLowerCase().contains("no coinciden"));

        verify(userRepository, times(1)).existsByUsername("mark");
        verify(userRepository, times(1)).existsByEmail("mark@example.com");
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
        verifyNoMoreInteractions(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("loadUserByUsername: usuario existente -> devuelve UserDetails correcto")
    public void loadUserByUsername_existingUser_returnsUserDetails() {
        // Arrange
        User user = User.builder()
                .username("alice")
                .firstName("Alice")
                .email("alice@example.com")
                .password("encodedPwd")
                .role(Role.ROLE_USER)
                .build();

        when(userRepository.findByUsername("alice")).thenReturn(Optional.of(user));

        // Act
        UserDetails details = userService.loadUserByUsername("alice");

        // Assert
        assertNotNull(details);
        assertEquals("alice", details.getUsername());
        assertSame(user, details);

        // Verificar que tiene autoridad correcta
        assertFalse(details.getAuthorities().isEmpty());
        assertTrue(details.getAuthorities().stream()
                .anyMatch(a -> a != null && a.getAuthority() != null &&
                        a.getAuthority().equals(Role.ROLE_USER.name())));

        verify(userRepository, times(1)).findByUsername("alice");
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    @DisplayName("loadUserByUsername: usuario no existente -> lanza UsernameNotFoundException")
    public void loadUserByUsername_nonExisting_throwsUsernameNotFoundException() {
        // Arrange
        when(userRepository.findByUsername("nope")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nope"));

        verify(userRepository, times(1)).findByUsername("nope");
        verifyNoMoreInteractions(userRepository);
    }
}