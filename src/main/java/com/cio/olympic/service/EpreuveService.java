package com.cio.olympic.service;

import com.cio.olympic.model.Epreuve;
import com.cio.olympic.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EpreuveService {

    @Autowired
    private EpreuveRepository epreuveRepository;

    public List<Epreuve> findAll() {
        return epreuveRepository.findAll();
    }

    public Epreuve findById(Long id) {
        return epreuveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Epreuve non trouvee avec id: " + id));
    }

    public Epreuve create(Epreuve epreuve) {
        return epreuveRepository.save(epreuve);
    }

    public Epreuve update(Long id, Epreuve details) {
        Epreuve epreuve = findById(id);
        epreuve.setNom(details.getNom());
        epreuve.setDateEpreuve(details.getDateEpreuve());
        epreuve.setGenre(details.getGenre());
        epreuve.setDiscipline(details.getDiscipline());
        return epreuveRepository.save(epreuve);
    }

    public void delete(Long id) {
        Epreuve epreuve = findById(id);
        epreuveRepository.delete(epreuve);
    }

    public List<Epreuve> findByDiscipline(Long disciplineId) {
        return epreuveRepository.findByDisciplineId(disciplineId);
    }

    public List<Epreuve> findByDate(LocalDate date) {
        return epreuveRepository.findByDateEpreuve(date);
    }
}