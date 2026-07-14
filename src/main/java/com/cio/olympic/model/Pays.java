package com.cio.olympic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pays")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom;

    @Column(name = "code_iso", nullable = false, unique = true, length = 3)
    private String codeIso;

    @JsonIgnore
    @OneToMany(mappedBy = "pays", cascade = CascadeType.ALL)
    private List<Athlete> athletes;
}