package com.cio.olympic.service;

import com.cio.olympic.model.Athlete;
import com.cio.olympic.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AthleteService {

    @Autowired
    private AthleteRepository athleteRepository;

    public List<Athlete> findAll() {
        return athleteRepository.findAll();
    }

    public Athlete findById(Long id) {
        return athleteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Athlete non trouve avec id: " + id));
    }

    public Athlete create(Athlete athlete) {
        return athleteRepository.save(athlete);
    }

    // Mise a jour complete
    public Athlete update(Long id, Athlete details) {
        Athlete athlete = findById(id);
        athlete.setNom(details.getNom());
        athlete.setPrenom(details.getPrenom());
        athlete.setSexe(details.getSexe());
        athlete.setDateNaissance(details.getDateNaissance());
        athlete.setTaille(details.getTaille());
        athlete.setPoids(details.getPoids());
        athlete.setPays(details.getPays());
        athlete.setDiscipline(details.getDiscipline());
        return athleteRepository.save(athlete);
    }

    // Mise a jour partielle (PATCH) : ne modifie que les champs non-nuls
    public Athlete partialUpdate(Long id, Athlete details) {
        Athlete athlete = findById(id);
        if (details.getNom() != null) athlete.setNom(details.getNom());
        if (details.getPrenom() != null) athlete.setPrenom(details.getPrenom());
        if (details.getSexe() != null) athlete.setSexe(details.getSexe());
        if (details.getDateNaissance() != null) athlete.setDateNaissance(details.getDateNaissance());
        if (details.getTaille() != null) athlete.setTaille(details.getTaille());
        if (details.getPoids() != null) athlete.setPoids(details.getPoids());
        if (details.getPays() != null) athlete.setPays(details.getPays());
        if (details.getDiscipline() != null) athlete.setDiscipline(details.getDiscipline());
        return athleteRepository.save(athlete);
    }

    public void delete(Long id) {
        Athlete athlete = findById(id);
        athleteRepository.delete(athlete);
    }

    public List<Athlete> findByDiscipline(Long disciplineId) {
        return athleteRepository.findByDisciplineId(disciplineId);
    }

    public List<Athlete> findByPays(Long paysId) {
        return athleteRepository.findByPaysId(paysId);
    }

    // Recherche multicritere (nom ou prenom)
    public List<Athlete> search(String query) {
        return athleteRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(query, query);
    }
}