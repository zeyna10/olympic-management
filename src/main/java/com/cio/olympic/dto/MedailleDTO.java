package com.cio.olympic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedailleDTO {
    private String pays;
    private String codeIso;
    private long or;
    private long argent;
    private long bronze;
    private long total;
}