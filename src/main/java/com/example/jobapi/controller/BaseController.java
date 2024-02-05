package com.example.jobapi.controller;

import com.example.jobapi.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    protected JobService jobService;
}
