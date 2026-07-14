package com.cio.olympic.rest.controller;

import com.cio.olympic.model.Athlete;
import com.cio.olympic.service.AthleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/athletes")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping
    public List<Athlete> getAll() {
        return athleteService.findAll();
    }

    @GetMapping("/{id}")
    public Athlete getById(@PathVariable Long id) {
        return athleteService.findById(id);
    }

    // Recherche multicritere : /api/athletes/search?q=usain
    @GetMapping("/search")
    public List<Athlete> search(@RequestParam String q) {
        return athleteService.search(q);
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<Athlete> getByDiscipline(@PathVariable Long disciplineId) {
        return athleteService.findByDiscipline(disciplineId);
    }

    @GetMapping("/pays/{paysId}")
    public List<Athlete> getByPays(@PathVariable Long paysId) {
        return athleteService.findByPays(paysId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Athlete create(@RequestBody Athlete athlete) {
        return athleteService.create(athlete);
    }

    // Modification complete
    @PutMapping("/{id}")
    public Athlete update(@PathVariable Long id, @RequestBody Athlete athlete) {
        return athleteService.update(id, athlete);
    }

    // Modification partielle
    @PatchMapping("/{id}")
    public Athlete partialUpdate(@PathVariable Long id, @RequestBody Athlete athlete) {
        return athleteService.partialUpdate(id, athlete);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        athleteService.delete(id);
    }
}