package com.cio.olympic.service;

import com.cio.olympic.dto.MedailleDTO;
import com.cio.olympic.model.Medaille;
import com.cio.olympic.model.Pays;
import com.cio.olympic.model.Resultat;
import com.cio.olympic.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedailleService {

    @Autowired
    private ResultatRepository resultatRepository;

    public List<MedailleDTO> getTableauMedailles() {
        List<Resultat> resultatsAvecMedaille = resultatRepository.findByMedailleNot(Medaille.AUCUNE);

        Map<Pays, List<Resultat>> parPays = resultatsAvecMedaille.stream()
                .collect(Collectors.groupingBy(r -> r.getAthlete().getPays()));

        List<MedailleDTO> tableau = new ArrayList<>();

        for (Map.Entry<Pays, List<Resultat>> entry : parPays.entrySet()) {
            Pays pays = entry.getKey();
            List<Resultat> resultats = entry.getValue();

            long or = resultats.stream().filter(r -> r.getMedaille() == Medaille.OR).count();
            long argent = resultats.stream().filter(r -> r.getMedaille() == Medaille.ARGENT).count();
            long bronze = resultats.stream().filter(r -> r.getMedaille() == Medaille.BRONZE).count();

            tableau.add(new MedailleDTO(
                    pays.getNom(),
                    pays.getCodeIso(),
                    or,
                    argent,
                    bronze,
                    or + argent + bronze
            ));
        }

        tableau.sort(
                Comparator.comparingLong(MedailleDTO::getOr).reversed()
                        .thenComparing(Comparator.comparingLong(MedailleDTO::getArgent).reversed())
                        .thenComparing(Comparator.comparingLong(MedailleDTO::getBronze).reversed())
        );

        return tableau;
    }
}