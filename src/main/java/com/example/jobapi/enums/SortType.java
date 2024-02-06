package com.example.jobapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SortType {
    ASC("sort ascending"),

    DESC("sort descending");

    @Getter
    private String sort;

    @Override
    public String toString() {
        return this.sort;
    }
}