package com.example.jobapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ApiStatus {

    SUCCESS("success"),

    FAIL("fail"),

    ERROR("error");

    @Getter
    private String status;

    @Override
    public String toString() {
        return this.status;
    }

}