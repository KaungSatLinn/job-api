package com.example.jobapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SuccessResponseCode {
    JA_001("List Retrieve Successfully");

    @Getter
    private String message;

    @Override
    public String toString() {
        return this.message;
    }
}
