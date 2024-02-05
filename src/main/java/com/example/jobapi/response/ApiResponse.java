package com.example.jobapi.response;

import com.example.jobapi.enums.ApiStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
    @Builder.Default
    private String status = ApiStatus.SUCCESS.toString();

    private String message;

    private T data;
}