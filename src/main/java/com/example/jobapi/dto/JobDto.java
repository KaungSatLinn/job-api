package com.example.jobapi.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("fieldsFilter")
public class JobDto {
    private Long id;
    private String timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfEmployer;
    private String salary;
    private String salaryComparison;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValue;
    private String gender;
    private String additionalComments;
    private List<String> fields;
    private String sort;
    private String sortTypes;
}