package com.exo2.Exercice2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "party",indexes = {
        @Index(name = "idx_party_location", columnList = "location")
})
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE,}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur userOwner;

    private String name;
    private String description;
    private String location;
    private Timestamp date;
    private int maxParticipants;
    private boolean isPaid;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE,}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "party_participants",
            joinColumns = @JoinColumn(name = "party_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @BatchSize(size = 10)
    private List<Utilisateur> participants;

}
