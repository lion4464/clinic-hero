package com.rustambek.clinic.analyse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyseDto {
    private Long id;
    private String name;
    private Long price;
}
