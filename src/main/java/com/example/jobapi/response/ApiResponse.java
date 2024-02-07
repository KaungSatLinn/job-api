package com.example.jobapi.response;

import com.example.jobapi.enums.ApiStatus;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ApiResponse<T> {
    @Builder.Default
    private String status = ApiStatus.SUCCESS.name();
    private String code;
    private String message;
    private T data;
}