package com.cio.olympic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassementPointsDTO {
    private String pays;
    private String codeIso;
    private long points;
    private long totalMedailles;
}