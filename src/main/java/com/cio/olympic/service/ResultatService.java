package com.cio.olympic.service;

import com.cio.olympic.model.Medaille;
import com.cio.olympic.model.Resultat;
import com.cio.olympic.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultat> findAll() {
        return resultatRepository.findAll();
    }

    public Resultat findById(Long id) {
        return resultatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resultat non trouve avec id: " + id));
    }

    // Enregistrement d'un resultat + attribution automatique de la medaille
    public Resultat create(Resultat resultat) {
        resultat.setMedaille(calculerMedaille(resultat.getPosition()));
        return resultatRepository.save(resultat);
    }

    public Resultat update(Long id, Resultat details) {
        Resultat resultat = findById(id);
        resultat.setPosition(details.getPosition());
        resultat.setPerformance(details.getPerformance());
        resultat.setEpreuve(details.getEpreuve());
        resultat.setAthlete(details.getAthlete());
        resultat.setMedaille(calculerMedaille(details.getPosition()));
        return resultatRepository.save(resultat);
    }

    public void delete(Long id) {
        Resultat resultat = findById(id);
        resultatRepository.delete(resultat);
    }

    private Medaille calculerMedaille(Integer position) {
        if (position == null) return Medaille.AUCUNE;
        return switch (position) {
            case 1 -> Medaille.OR;
            case 2 -> Medaille.ARGENT;
            case 3 -> Medaille.BRONZE;
            default -> Medaille.AUCUNE;
        };
    }

    // Podium d'une epreuve : les 3 premiers, tries par position
    public List<Resultat> getPodium(Long epreuveId) {
        return resultatRepository.findByEpreuveIdOrderByPositionAsc(epreuveId)
                .stream()
                .filter(r -> r.getPosition() != null && r.getPosition() <= 3)
                .toList();
    }

    public List<Resultat> findByEpreuve(Long epreuveId) {
        return resultatRepository.findByEpreuveId(epreuveId);
    }

    public List<Resultat> findByAthlete(Long athleteId) {
        return resultatRepository.findByAthleteId(athleteId);
    }
}