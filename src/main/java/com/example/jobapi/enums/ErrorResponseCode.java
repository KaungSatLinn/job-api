package com.example.jobapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorResponseCode {
    ER_001("Wrong Fields.");

    @Getter
    private String message;

    @Override
    public String toString() {
        return this.message;
    }
}
