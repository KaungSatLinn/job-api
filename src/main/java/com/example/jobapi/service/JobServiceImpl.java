package com.example.jobapi.service;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import com.example.jobapi.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getJobData(JobDto jobDto) {
        List<Job> jobs = jobRepository.findJobDataByFilterdVal(jobDto);
        return jobs;
    }
}
