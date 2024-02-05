package com.example.jobapi.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job{
    private LocalDateTime timestamp;
    private String employer;
    private String location;
    private String jobTitle;
    private String yearsAtEmployer;
    private String yearsOfEmployer;
    private String salary;
    private String signingBonus;
    private String annualBonus;
    private String annualStockValue;
    private String gender;
    private String additionalComments;
}
