package com.cio.olympic.rest.controller;

import com.cio.olympic.model.Resultat;
import com.cio.olympic.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {

    @Autowired
    private ResultatService resultatService;

    @GetMapping
    public List<Resultat> getAll() {
        return resultatService.findAll();
    }

    @GetMapping("/{id}")
    public Resultat getById(@PathVariable Long id) {
        return resultatService.findById(id);
    }

    @GetMapping("/epreuve/{epreuveId}")
    public List<Resultat> getByEpreuve(@PathVariable Long epreuveId) {
        return resultatService.findByEpreuve(epreuveId);
    }

    @GetMapping("/athlete/{athleteId}")
    public List<Resultat> getByAthlete(@PathVariable Long athleteId) {
        return resultatService.findByAthlete(athleteId);
    }

    // Podium (top 3) d'une epreuve : /api/resultats/epreuve/5/podium
    @GetMapping("/epreuve/{epreuveId}/podium")
    public List<Resultat> getPodium(@PathVariable Long epreuveId) {
        return resultatService.getPodium(epreuveId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Resultat create(@RequestBody Resultat resultat) {
        return resultatService.create(resultat);
    }

    @PutMapping("/{id}")
    public Resultat update(@PathVariable Long id, @RequestBody Resultat resultat) {
        return resultatService.update(id, resultat);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        resultatService.delete(id);
    }
}