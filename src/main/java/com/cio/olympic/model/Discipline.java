package com.cio.olympic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "discipline")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(length = 1000)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Athlete> athletes;

    @JsonIgnore
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Epreuve> epreuves;
}