package com.example.jobapi.repositoryCustom;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface JobRepositoryCustom {
    List<Job> findJobDataByFilterdVal(JobDto jobDto);
}
