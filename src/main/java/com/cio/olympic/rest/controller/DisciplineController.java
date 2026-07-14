package com.cio.olympic.rest.controller;

import com.cio.olympic.model.Athlete;
import com.cio.olympic.model.Discipline;
import com.cio.olympic.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<Discipline> getAll() {
        return disciplineService.findAll();
    }

    @GetMapping("/{id}")
    public Discipline getById(@PathVariable Long id) {
        return disciplineService.findById(id);
    }

    @GetMapping("/{id}/athletes")
    public List<Athlete> getAthletes(@PathVariable Long id) {
        return disciplineService.findAthletesByDiscipline(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Discipline create(@RequestBody Discipline discipline) {
        return disciplineService.create(discipline);
    }

    @PutMapping("/{id}")
    public Discipline update(@PathVariable Long id, @RequestBody Discipline discipline) {
        return disciplineService.update(id, discipline);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        disciplineService.delete(id);
    }
}