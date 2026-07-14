package com.cio.olympic.rest.controller;

import com.cio.olympic.dto.ClassementPointsDTO;
import com.cio.olympic.dto.StatistiquesDTO;
import com.cio.olympic.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/statistiques")
    public StatistiquesDTO getStatistiques() {
        return dashboardService.getStatistiquesGenerales();
    }

    @GetMapping("/classement-points")
    public List<ClassementPointsDTO> getClassementParPoints() {
        return dashboardService.getClassementParPoints();
    }

    @GetMapping("/medailles-par-pays")
    public Map<String, Long> getMedaillesParPays() {
        return dashboardService.getMedaillesParPays();
    }
}