package com.example.jobapi.mapper;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(target = "salary", source = "salary")
    @Mapping(target = "salaryComparison", source = "salaryComparison")
    @Mapping(target = "jobTitle", source = "jobTitle")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "fields", source = "fields")
    @Mapping(target = "sort", source = "sort")
    @Mapping(target = "sortTypes", source = "sortTypes")
    JobDto toJobDto(String salary, String salaryComparison, String jobTitle, String gender, List<String> fields, String sort, String sortTypes);
}