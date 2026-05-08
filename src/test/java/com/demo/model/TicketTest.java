package com.demo.model;

import com.demo.model.enums.BuyStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias de la entidad Ticket")
class TicketTest {

    @Test
    @DisplayName("Debería crear un Ticket correctamente con todos sus atributos y relaciones")
    void fullTicketBuilderTest() {
        // 1. Setup de dependencias (Objetos relacionados)
        User mockUser = User.builder()
                .id(1L)
                .email("test@example.com")
                .build();

        Session mockSession = Session.builder()
                .id(10L)
                .startTime(LocalDateTime.now().plusDays(1))
                .build();

        // 2. Ejecución del Builder
        Ticket ticket = Ticket.builder()
                .id(500L)
                .row("H")
                .seat("12")
                .price(9.50)
                .discount(1.50)
                .QRCode("QR-INT-2026-XYZ")
                .user(mockUser)
                .session(mockSession)
                .build();

        // 3. Verificaciones (Assertions)
        assertAll("Verificación de atributos del Ticket",
                () -> assertEquals(500L, ticket.getId()),
                () -> assertEquals("H", ticket.getRow()),
                () -> assertEquals("12", ticket.getSeat()),
                () -> assertEquals(9.50, ticket.getPrice()),
                () -> assertEquals(1.50, ticket.getDiscount()),
                () -> assertEquals("QR-INT-2026-XYZ", ticket.getQRCode())
        );

        assertAll("Verificación de relaciones y valores por defecto",
                () -> assertNotNull(ticket.getUser(), "El ticket debe tener un usuario asociado"),
                () -> assertNotNull(ticket.getSession(), "El ticket debe estar vinculado a una sesión"),
                // Verificación de los valores marcados con @Builder.Default
                () -> assertEquals(BuyStatus.LIBRE, ticket.getStatus(), "El estado inicial debería ser LIBRE"),
                () -> assertNotNull(ticket.getBuyDateTime(), "La fecha de compra debería generarse automáticamente")
        );
    }

    @Test
    @DisplayName("Debería reflejar correctamente el cambio de estado de LIBRE a PAGADO")
    void statusChangeTest() {
        Ticket ticket = Ticket.builder()
                .status(BuyStatus.PAGADO) // Forzamos un estado distinto al default
                .build();

        assertEquals(BuyStatus.PAGADO, ticket.getStatus());
        assertNotEquals(BuyStatus.LIBRE, ticket.getStatus());
    }

    @Test
    @DisplayName("Verificación de método ToString (excluyendo relaciones según la entidad)")
    void toStringExclusionTest() {
        Ticket ticket = Ticket.builder()
                .id(1L)
                .row("A")
                .build();

        String toString = ticket.toString();

        assertTrue(toString.contains("row=A"));
        // Comprobamos que respeta el @ToString(exclude={"session","user"})
        assertFalse(toString.contains("session="), "ToString no debería incluir la sesión para evitar recursividad");
        assertFalse(toString.contains("user="), "ToString no debería incluir el usuario");
    }
}
