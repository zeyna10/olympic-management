package com.cio.olympic.repository;

import com.cio.olympic.model.Medaille;
import com.cio.olympic.model.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByEpreuveId(Long epreuveId);
    List<Resultat> findByAthleteId(Long athleteId);
    List<Resultat> findByEpreuveIdOrderByPositionAsc(Long epreuveId); // pour le podium
    List<Resultat> findByAthlete_Pays_IdAndMedailleNot(Long paysId, Medaille medaille);
    List<Resultat> findByMedailleNot(Medaille medaille);
}
