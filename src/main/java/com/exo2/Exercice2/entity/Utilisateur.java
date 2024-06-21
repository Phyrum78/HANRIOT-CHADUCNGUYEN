package com.exo2.Exercice2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "utilisateur", indexes = {
        @Index(name = "idx_email", columnList = "email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateur_id")
    private Long id;

    private String email;

    private String password;
    private String city;
    private String region;
    private int age;
    private String interests;

    @PrePersist
    protected void onCreate() {
        LocalDateTime createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        LocalDateTime updatedAt = LocalDateTime.now();
    }
}
