package com.cio.olympic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "epreuve")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(name = "date_epreuve", nullable = false)
    private LocalDate dateEpreuve;

    @Column(length = 20)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "discipline_id", nullable = false)
    private Discipline discipline;

    @JsonIgnore
    @OneToMany(mappedBy = "epreuve", cascade = CascadeType.ALL)
    private List<Resultat> resultats;
}