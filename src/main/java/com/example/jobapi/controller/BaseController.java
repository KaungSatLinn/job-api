package com.example.jobapi.controller;

import com.example.jobapi.response.ApiResponse;
import com.example.jobapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BaseController<T> {

    protected ResponseEntity<ApiResponse<List<T>>> okResponse(String message, List<T> data) {

        ApiResponse<List<T>> responses = ApiResponse.<List<T>>builder()
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.ok(responses);
    }
}
