package com.cio.olympic.rest.controller;

import com.cio.olympic.model.Epreuve;
import com.cio.olympic.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/epreuves")
public class EpreuveController {

    @Autowired
    private EpreuveService epreuveService;

    @GetMapping
    public List<Epreuve> getAll() {
        return epreuveService.findAll();
    }

    @GetMapping("/{id}")
    public Epreuve getById(@PathVariable Long id) {
        return epreuveService.findById(id);
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<Epreuve> getByDiscipline(@PathVariable Long disciplineId) {
        return epreuveService.findByDiscipline(disciplineId);
    }

    // Recherche par date : /api/epreuves/date?jour=2026-08-05
    @GetMapping("/date")
    public List<Epreuve> getByDate(@RequestParam("jour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return epreuveService.findByDate(date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Epreuve create(@RequestBody Epreuve epreuve) {
        return epreuveService.create(epreuve);
    }

    @PutMapping("/{id}")
    public Epreuve update(@PathVariable Long id, @RequestBody Epreuve epreuve) {
        return epreuveService.update(id, epreuve);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        epreuveService.delete(id);
    }
}