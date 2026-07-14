package com.cio.olympic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatistiquesDTO {
    private long totalAthletes;
    private long totalPays;
    private long totalMedaillesOr;
    private long totalMedaillesArgent;
    private long totalMedaillesBronze;
}