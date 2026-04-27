package com.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import com.demo.model.Session;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name="tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=2)
    private String row;

    @Column(length=3)
    private String seat;

    private Double price;

    private Double discont;

    private LocalDateTime buyDateTime;

    private BuyStatus status;

    private String QRCode;

    @ToString.Exclude
    @ManyToOne
    private User user;

    @ToString.Exclude
    @ManyToOne
    private Session session;



}
