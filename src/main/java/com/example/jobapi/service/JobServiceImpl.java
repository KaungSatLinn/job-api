package com.example.jobapi.service;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.mapper.JobMapper;
import com.example.jobapi.model.Job;
import com.example.jobapi.repository.JobRepository;
import com.example.jobapi.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService{
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    JobMapper jobMapper;

    @Override
    public List<Object[]> getFilteredJobDataWithDynamicCol(JobDto jobDto){
        List<Object[]> jobs = jobRepository.findJobDataByFilteredVal(jobDto);
        return jobs;
    }
}