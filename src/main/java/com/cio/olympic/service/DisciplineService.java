package com.cio.olympic.service;

import com.cio.olympic.model.Athlete;
import com.cio.olympic.model.Discipline;
import com.cio.olympic.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Discipline> findAll() {
        return disciplineRepository.findAll();
    }

    public Discipline findById(Long id) {
        return disciplineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discipline non trouvee avec id: " + id));
    }

    public Discipline create(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public Discipline update(Long id, Discipline disciplineDetails) {
        Discipline discipline = findById(id);
        discipline.setNom(disciplineDetails.getNom());
        discipline.setDescription(disciplineDetails.getDescription());
        return disciplineRepository.save(discipline);
    }

    public void delete(Long id) {
        Discipline discipline = findById(id);
        disciplineRepository.delete(discipline);
    }

    public List<Athlete> findAthletesByDiscipline(Long disciplineId) {
        return findById(disciplineId).getAthletes();
    }
}