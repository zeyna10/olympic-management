package com.cio.olympic.repository;

import com.cio.olympic.model.Epreuve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EpreuveRepository extends JpaRepository<Epreuve, Long> {
    List<Epreuve> findByDisciplineId(Long disciplineId);
    List<Epreuve> findByDateEpreuve(LocalDate date);
}