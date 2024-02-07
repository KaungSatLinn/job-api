package com.example.jobapi.controller;

import com.example.jobapi.enums.ApiStatus;
import com.example.jobapi.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController {

    protected ResponseEntity<?> okResponse(Enum code, List<?> data) {
        ApiResponse<List<?>> responses = ApiResponse.<List<?>>builder()
                .code(code.name())
                .message(code.toString())
                .data(data)
                .build();
        return ResponseEntity.ok(responses);
    }

    protected ResponseEntity<ApiResponse<?>> badRequestResponse(Enum code, Object data) {
        ApiResponse<?> response = ApiResponse.<Object>builder()
                .status(ApiStatus.FAIL.name())
                .code(code.name())
                .message(code.toString())
                .data(data)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}