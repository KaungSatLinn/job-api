package com.example.jobapi.controller;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import com.example.jobapi.service.JobService;
import com.example.jobapi.util.CommonUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class JobController{

    @Autowired
    protected JobService jobService;

    @GetMapping("/job_data")
    public ResponseEntity<?> getJobData(@RequestParam(name = "salary", required = false) String minSalary,
                                        @RequestParam(name = "salary_comparison", required = false) String salaryComparison,
                                        @RequestParam(name = "salary", required = false) String maxSalary,
                                        @RequestParam(name = "job_title", required = false) String jobTitles,
                                        @RequestParam(name = "gender", required = false) String gender,
                                        @RequestParam(name = "fields", required = false) List<String> fields,
                                        @RequestParam(name = "sort", required = false) String sort,
                                        @RequestParam(name = "sort_types", required = false) String sortTypes) throws JsonProcessingException {

        JobDto dto = new JobDto();
        dto.setGender(gender);
        dto.setFields(fields);
        List<Job> jobs = jobService.getJobData(dto);
        String str = CommonUtil.convertToJson(jobs, fields);
        return new ResponseEntity<String>(str, HttpStatus.OK);
    }
}
