package com.cio.olympic.repository;

import com.cio.olympic.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    List<Athlete> findByDisciplineId(Long disciplineId);
    List<Athlete> findByPaysId(Long paysId);
    List<Athlete> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
    long countByPaysId(Long paysId);
}