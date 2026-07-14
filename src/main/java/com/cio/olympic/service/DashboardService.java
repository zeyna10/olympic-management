package com.cio.olympic.service;

import com.cio.olympic.dto.ClassementPointsDTO;
import com.cio.olympic.dto.StatistiquesDTO;
import com.cio.olympic.model.Medaille;
import com.cio.olympic.model.Pays;
import com.cio.olympic.model.Resultat;
import com.cio.olympic.repository.AthleteRepository;
import com.cio.olympic.repository.PaysRepository;
import com.cio.olympic.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private AthleteRepository athleteRepository;

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private ResultatRepository resultatRepository;

    public StatistiquesDTO getStatistiquesGenerales() {
        long totalAthletes = athleteRepository.count();
        long totalPays = paysRepository.count();

        List<Resultat> resultatsMedailles = resultatRepository.findByMedailleNot(Medaille.AUCUNE);
        long or = resultatsMedailles.stream().filter(r -> r.getMedaille() == Medaille.OR).count();
        long argent = resultatsMedailles.stream().filter(r -> r.getMedaille() == Medaille.ARGENT).count();
        long bronze = resultatsMedailles.stream().filter(r -> r.getMedaille() == Medaille.BRONZE).count();

        return new StatistiquesDTO(totalAthletes, totalPays, or, argent, bronze);
    }

    // Classement par points : Or=7, Argent=4, Bronze=1
    public List<ClassementPointsDTO> getClassementParPoints() {
        List<Resultat> resultatsMedailles = resultatRepository.findByMedailleNot(Medaille.AUCUNE);

        Map<Pays, List<Resultat>> parPays = resultatsMedailles.stream()
                .collect(Collectors.groupingBy(r -> r.getAthlete().getPays()));

        List<ClassementPointsDTO> classement = new ArrayList<>();

        for (Map.Entry<Pays, List<Resultat>> entry : parPays.entrySet()) {
            Pays pays = entry.getKey();
            List<Resultat> resultats = entry.getValue();

            long or = resultats.stream().filter(r -> r.getMedaille() == Medaille.OR).count();
            long argent = resultats.stream().filter(r -> r.getMedaille() == Medaille.ARGENT).count();
            long bronze = resultats.stream().filter(r -> r.getMedaille() == Medaille.BRONZE).count();

            long points = (or * 7) + (argent * 4) + (bronze * 1);

            classement.add(new ClassementPointsDTO(
                    pays.getNom(),
                    pays.getCodeIso(),
                    points,
                    or + argent + bronze
            ));
        }

        classement.sort(Comparator.comparingLong(ClassementPointsDTO::getPoints).reversed());
        return classement;
    }

    // Nombre de medailles par pays (tous types confondus)
    public Map<String, Long> getMedaillesParPays() {
        List<Resultat> resultatsMedailles = resultatRepository.findByMedailleNot(Medaille.AUCUNE);

        return resultatsMedailles.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getAthlete().getPays().getNom(),
                        Collectors.counting()
                ));
    }
}