package com.example.jobapi.service;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;

import java.util.List;

public interface JobService {
    List<Job> getJobData(JobDto jobDto);
}
