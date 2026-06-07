package com.demo.service;

import com.demo.model.Room;
import com.demo.model.Session;
import com.demo.model.Ticket;
import com.demo.model.User;
import com.demo.model.enums.BuyStatus;
import com.demo.repository.RoomRepository;
import com.demo.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private TicketService ticketService;

    private Room room;
    private Session session;

    @BeforeEach
    public void setUp() {
        room = Room.builder()
                .id(1L)
                .name("Sala 1")
                .capacity(4)
                .build();

        session = Session.builder()
                .id(1L)
                .price(9.50)
                .room(room)
                .build();
    }

    @Test
    @DisplayName("findAll: devuelve todos los tickets")
    public void findAll_returnsAllTickets() {
        // Arrange
        List<Ticket> tickets = List.of(
                Ticket.builder().id(1L).row("A").seat("1").build(),
                Ticket.builder().id(2L).row("A").seat("2").build()
        );

        when(ticketRepository.findAll()).thenReturn(tickets);

        // Act
        List<Ticket> result = ticketService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("A", result.get(0).getRow());
        assertEquals("1", result.get(0).getSeat());

        verify(ticketRepository, times(1)).findAll();
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("findById: ticket existente -> devuelve Optional con ticket")
    public void findById_existingTicket_returnsOptionalWithTicket() {
        // Arrange
        Ticket ticket = Ticket.builder()
                .id(1L)
                .row("B")
                .seat("5")
                .status(BuyStatus.LIBRE)
                .build();

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        Optional<Ticket> result = ticketService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        assertEquals("B", result.get().getRow());
        assertEquals("5", result.get().getSeat());
        assertEquals(BuyStatus.LIBRE, result.get().getStatus());

        verify(ticketRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("save: ticket válido -> guarda ticket")
    public void save_validTicket_savesTicket() {
        // Arrange
        Ticket ticket = Ticket.builder()
                .row("C")
                .seat("3")
                .price(9.50)
                .status(BuyStatus.LIBRE)
                .build();

        when(ticketRepository.save(ticket)).thenReturn(ticket);

        // Act
        Ticket saved = ticketService.save(ticket);

        // Assert
        assertNotNull(saved);
        assertEquals("C", saved.getRow());
        assertEquals("3", saved.getSeat());
        assertEquals(9.50, saved.getPrice());
        assertEquals(BuyStatus.LIBRE, saved.getStatus());

        verify(ticketRepository, times(1)).save(ticket);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("getBySessionId: devuelve tickets de una sesión")
    public void getBySessionId_returnsTicketsFromSession() {
        // Arrange
        List<Ticket> tickets = List.of(
                Ticket.builder().id(1L).session(session).build(),
                Ticket.builder().id(2L).session(session).build()
        );

        when(ticketRepository.findBySession_Id(1L)).thenReturn(tickets);

        // Act
        List<Ticket> result = ticketService.getBySessionId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(ticketRepository, times(1)).findBySession_Id(1L);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("getByMovieId: devuelve tickets asociados a una película")
    public void getByMovieId_returnsTicketsFromMovie() {
        // Arrange
        List<Ticket> tickets = List.of(
                Ticket.builder().id(1L).build(),
                Ticket.builder().id(2L).build()
        );

        when(ticketRepository.findBySession_Movie_Id(10L)).thenReturn(tickets);

        // Act
        List<Ticket> result = ticketService.getByMovieId(10L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(ticketRepository, times(1)).findBySession_Movie_Id(10L);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("getByUserId: devuelve tickets de un usuario")
    public void getByUserId_returnsTicketsFromUser() {
        // Arrange
        List<Ticket> tickets = List.of(
                Ticket.builder().id(1L).build()
        );

        when(ticketRepository.findByUser_Id(7L)).thenReturn(tickets);

        // Act
        List<Ticket> result = ticketService.getByUserId(7L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());

        verify(ticketRepository, times(1)).findByUser_Id(7L);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sesión con sala válida -> genera tickets según capacidad")
    public void generarTickets_withValidSessionAndRoom_generatesTicketsByRoomCapacity() {
        // Arrange
        when(roomRepository.findById(1L)).thenReturn(Optional.of(room));
        when(ticketRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        List<Ticket> result = ticketService.generarTickets(session);

        // Assert
        assertNotNull(result);
        assertEquals(4, result.size());

        assertEquals("A", result.get(0).getRow());
        assertEquals("1", result.get(0).getSeat());
        assertEquals(9.50, result.get(0).getPrice());
        assertEquals(0.0, result.get(0).getDiscount());
        assertEquals(BuyStatus.LIBRE, result.get(0).getStatus());
        assertSame(session, result.get(0).getSession());

        ArgumentCaptor<List<Ticket>> captor = ArgumentCaptor.forClass(List.class);
        verify(ticketRepository, times(1)).saveAll(captor.capture());

        List<Ticket> savedTickets = captor.getValue();
        assertEquals(4, savedTickets.size());

        verify(roomRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sesión sin sala -> devuelve lista vacía")
    public void generarTickets_withoutRoom_returnsEmptyList() {
        // Arrange
        Session sessionWithoutRoom = Session.builder()
                .id(1L)
                .price(9.50)
                .room(null)
                .build();

        // Act
        List<Ticket> result = ticketService.generarTickets(sessionWithoutRoom);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verifyNoInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sala sin id -> devuelve lista vacía")
    public void generarTickets_withRoomWithoutId_returnsEmptyList() {
        // Arrange
        Room roomWithoutId = Room.builder()
                .name("Sala sin id")
                .capacity(4)
                .build();

        Session sessionWithRoomWithoutId = Session.builder()
                .id(1L)
                .price(9.50)
                .room(roomWithoutId)
                .build();

        // Act
        List<Ticket> result = ticketService.generarTickets(sessionWithRoomWithoutId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verifyNoInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sala con capacidad nula -> devuelve lista vacía")
    public void generarTickets_withNullCapacity_returnsEmptyList() {
        // Arrange
        Room roomWithoutCapacity = Room.builder()
                .id(1L)
                .name("Sala sin capacidad")
                .capacity(null)
                .build();

        when(roomRepository.findById(1L)).thenReturn(Optional.of(roomWithoutCapacity));

        // Act
        List<Ticket> result = ticketService.generarTickets(session);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(roomRepository, times(1)).findById(1L);
        verify(ticketRepository, never()).saveAll(anyList());
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sala con capacidad cero -> devuelve lista vacía")
    public void generarTickets_withZeroCapacity_returnsEmptyList() {
        // Arrange
        Room roomWithZeroCapacity = Room.builder()
                .id(1L)
                .name("Sala vacía")
                .capacity(0)
                .build();

        when(roomRepository.findById(1L)).thenReturn(Optional.of(roomWithZeroCapacity));

        // Act
        List<Ticket> result = ticketService.generarTickets(session);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(roomRepository, times(1)).findById(1L);
        verify(ticketRepository, never()).saveAll(anyList());
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("generarTickets: sala inexistente -> lanza NoSuchElementException")
    public void generarTickets_withNonExistingRoom_throwsNoSuchElementException() {
        // Arrange
        when(roomRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> ticketService.generarTickets(session));

        verify(roomRepository, times(1)).findById(1L);
        verify(ticketRepository, never()).saveAll(anyList());
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("processCheckout: ticket existente con snacks y usuario -> marca como pagado y guarda")
    public void processCheckout_existingTicketWithSnacksAndUser_marksAsPaidAndSaves() {
        // Arrange
        User user = User.builder()
                .id(5L)
                .username("john")
                .email("john@example.com")
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .session(session)
                .row("A")
                .seat("1")
                .status(BuyStatus.LIBRE)
                .build();

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        ticketService.processCheckout(
                1L,
                user,
                1, // palomitas medianas = 4.50
                1, // palomitas grandes = 6.00
                1, // refresco = 3.00
                0,
                0
        );

        // Assert
        assertEquals(BuyStatus.PAGADO, ticket.getStatus());
        assertEquals(9.50, ticket.getPrice());
        assertEquals(13.50, ticket.getSnackPrice());
        assertNotNull(ticket.getBuyDateTime());
        assertSame(user, ticket.getUser());
        assertNotNull(ticket.getQRCode());
        assertTrue(ticket.getQRCode().startsWith("ONLYFILM-1-"));

        verify(ticketRepository, times(1)).findById(1L);
        verify(ticketRepository, times(1)).save(ticket);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("processCheckout: ticket existente sin snacks -> snackPrice queda null")
    public void processCheckout_existingTicketWithoutSnacks_setsSnackPriceNull() {
        // Arrange
        Ticket ticket = Ticket.builder()
                .id(1L)
                .session(session)
                .status(BuyStatus.LIBRE)
                .build();

        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        // Act
        ticketService.processCheckout(
                1L,
                null,
                0,
                0,
                0,
                0,
                0
        );

        // Assert
        assertEquals(BuyStatus.PAGADO, ticket.getStatus());
        assertEquals(9.50, ticket.getPrice());
        assertNull(ticket.getSnackPrice());
        assertNull(ticket.getUser());
        assertNotNull(ticket.getBuyDateTime());
        assertNotNull(ticket.getQRCode());
        assertTrue(ticket.getQRCode().startsWith("ONLYFILM-1-"));

        verify(ticketRepository, times(1)).findById(1L);
        verify(ticketRepository, times(1)).save(ticket);
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }

    @Test
    @DisplayName("processCheckout: ticket inexistente -> lanza NoSuchElementException")
    public void processCheckout_nonExistingTicket_throwsNoSuchElementException() {
        // Arrange
        when(ticketRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoSuchElementException.class, () ->
                ticketService.processCheckout(
                        99L,
                        null,
                        0,
                        0,
                        0,
                        0,
                        0
                )
        );

        verify(ticketRepository, times(1)).findById(99L);
        verify(ticketRepository, never()).save(any());
        verifyNoMoreInteractions(ticketRepository, roomRepository);
    }
}
