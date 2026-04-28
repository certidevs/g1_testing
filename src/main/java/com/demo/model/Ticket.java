package com.demo.model;

import com.demo.model.enums.BuyStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude={"user","session"})
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

    @Builder.Default
    private LocalDateTime buyDateTime = LocalDateTime.now();

    @Builder.Default
    @Column
    private BuyStatus status = BuyStatus.LIBRE;

    private String QRCode;

//    @ManyToOne
//    private User user;

    @ManyToOne
    private Session session;



}
