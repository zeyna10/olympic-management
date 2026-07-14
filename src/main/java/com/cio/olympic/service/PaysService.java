package com.cio.olympic.service;

import com.cio.olympic.model.Pays;
import com.cio.olympic.repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaysService {

    @Autowired
    private PaysRepository paysRepository;

    public List<Pays> findAll() {
        return paysRepository.findAll();
    }

    public Pays findById(Long id) {
        return paysRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pays non trouve avec id: " + id));
    }

    public Pays create(Pays pays) {
        return paysRepository.save(pays);
    }

    public Pays update(Long id, Pays paysDetails) {
        Pays pays = findById(id);
        pays.setNom(paysDetails.getNom());
        pays.setCodeIso(paysDetails.getCodeIso());
        return paysRepository.save(pays);
    }

    public void delete(Long id) {
        Pays pays = findById(id);
        paysRepository.delete(pays);
    }
}