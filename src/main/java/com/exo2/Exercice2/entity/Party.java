package com.exo2.Exercice2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "party")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Party {

    @Enumerated(EnumType.STRING)
    PartyType partyType = PartyType.CLASSIC;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "party_id")
    private Long id;

    private String name;
    private String description;
    private String location;
    private Timestamp date;
    private int maxParticipants;
    private boolean isPaid;

}
