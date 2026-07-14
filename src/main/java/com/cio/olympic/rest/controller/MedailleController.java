package com.cio.olympic.rest.controller;

import com.cio.olympic.dto.MedailleDTO;
import com.cio.olympic.service.MedailleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/medailles")
public class MedailleController {

    @Autowired
    private MedailleService medailleService;

    @GetMapping
    public List<MedailleDTO> getTableauMedailles() {
        return medailleService.getTableauMedailles();
    }
}