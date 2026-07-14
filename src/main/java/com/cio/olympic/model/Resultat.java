package com.cio.olympic.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resultat")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer position; // classement dans l'epreuve

    private String performance; // ex: "9.58s", "8.95m"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Medaille medaille = Medaille.AUCUNE;

    @ManyToOne
    @JoinColumn(name = "epreuve_id", nullable = false)
    private Epreuve epreuve;

    @ManyToOne
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;
}