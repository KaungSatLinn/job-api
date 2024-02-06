package com.example.jobapi.controller;

import com.example.jobapi.response.ApiResponse;
import com.example.jobapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BaseController {

    protected ResponseEntity<?> okResponse(String message, List<?> data) {
        ApiResponse<List<?>> responses = ApiResponse.<List<?>>builder()
                .message(message)
                .data(data)
                .build();
        return ResponseEntity.ok(responses);
    }
}