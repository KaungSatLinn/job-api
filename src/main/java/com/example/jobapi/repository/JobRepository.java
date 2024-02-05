package com.example.jobapi.repository;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import com.example.jobapi.repositoryCustom.JobRepositoryCustom;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {
}
