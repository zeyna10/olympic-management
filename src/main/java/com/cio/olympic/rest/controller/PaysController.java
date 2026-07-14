package com.cio.olympic.rest.controller;

import com.cio.olympic.model.Pays;
import com.cio.olympic.service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pays")
public class PaysController {

    @Autowired
    private PaysService paysService;

    @GetMapping
    public List<Pays> getAll() {
        return paysService.findAll();
    }

    @GetMapping("/{id}")
    public Pays getById(@PathVariable Long id) {
        return paysService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pays create(@RequestBody Pays pays) {
        return paysService.create(pays);
    }

    @PutMapping("/{id}")
    public Pays update(@PathVariable Long id, @RequestBody Pays pays) {
        return paysService.update(id, pays);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paysService.delete(id);
    }
}