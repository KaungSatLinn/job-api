package com.example.jobapi.controller;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.mapper.JobMapper;
import com.example.jobapi.model.Job;
import com.example.jobapi.response.ApiResponse;
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
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class JobController extends BaseController {

    @Autowired
    protected JobService jobService;

    @Autowired
    JobMapper jobMapper;

    @GetMapping("/job_data")
    public ResponseEntity<?> getJobData(@RequestParam(name = "salary", required = false) String salary,
            @RequestParam(name = "salary_comparison", required = false) String salaryComparison,
            @RequestParam(name = "job_title", required = false) String jobTitle,
            @RequestParam(name = "gender", required = false) String gender,
            @RequestParam(name = "fields", required = false) List<String> fields,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "sort_type", required = false) String sortType) throws JsonProcessingException {

        if (fields == null || fields.isEmpty()) {
            fields = CommonUtil.getFieldNames(Job.class);
        }

        JobDto dto = jobMapper.toJobDto(salary, salaryComparison, jobTitle, gender, fields, sort, sortType);

        List<Object[]> jobs = jobService.getFilteredJobDataWithDynamicCol(dto);

        List<Map<String, Object>> resultList = CommonUtil.convertToMapList(jobs, fields);

        return okResponse("list.retrieve.success", resultList);
    }
}