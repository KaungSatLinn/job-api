package com.example.jobapi.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SalaryComparison {
    GTE("Greater Than Or Equal"),
    EQUAL("Equal To"),
    LTE("Less Than Or Equal");

    @Getter
    @JsonValue
    private String comparison;

    @Override
    public String toString() {
        return this.comparison;
    }
}
