package com.example.jobapi.repositoryCustom;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.enums.SalaryComparison;
import com.example.jobapi.enums.SortType;
import com.example.jobapi.model.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class JobRepositoryCustomImpl implements JobRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findJobDataByFilteredVal(JobDto jobDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Job> root = query.from(Job.class);

        List<Predicate> predicates = new ArrayList<>();
        if (jobDto.getSalary() != null && !jobDto.getSalary().isEmpty()) {
            Expression<String> salaryExp = root.get("salary");
            Predicate condition = cb.and(cb.equal(salaryExp, jobDto.getSalary()));
            if(jobDto.getSalaryComparison().equalsIgnoreCase(SalaryComparison.GTE.name())){
                condition = cb.and(cb.greaterThanOrEqualTo(salaryExp, jobDto.getSalary()));
            }
            if(jobDto.getSalaryComparison().equalsIgnoreCase(SalaryComparison.LTE.name())){
                condition = cb.and(cb.lessThanOrEqualTo(salaryExp, jobDto.getSalary()));
            }
            predicates.add(condition);
        }
        if (jobDto.getGender() != null && !jobDto.getGender().isEmpty()) {
            Predicate condition = cb.and(cb.equal(root.get("gender"), jobDto.getGender()));
            predicates.add(condition);
        }
        if (jobDto.getJobTitle() != null && !jobDto.getJobTitle().isEmpty()) {
            Predicate condition = cb.and(cb.equal(root.get("jobTitle"), jobDto.getJobTitle()));
            predicates.add(condition);
        }
        if (jobDto.getFields() != null && !jobDto.getFields().isEmpty()) {
            Selection[] selections = jobDto.getFields().stream()
                    .map(root::get)
                    .toArray(Selection[]::new);
            query = query.select(cb.array(selections));
        }

        query.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        if (jobDto.getSort() != null && !jobDto.getSort().isEmpty()) {
            if(jobDto.getSortType() != null && !jobDto.getSortType().isEmpty() && jobDto.getSortType().equalsIgnoreCase(SortType.ASC.name())){
                query.orderBy(cb.asc(root.get(jobDto.getSort())));
            }
            if(jobDto.getSortType() != null && !jobDto.getSortType().isEmpty() && jobDto.getSortType().equalsIgnoreCase(SortType.DESC.name())){
                query.orderBy(cb.desc(root.get(jobDto.getSort())));
            }
        }
        return entityManager.createQuery(query)
                .getResultList();
    }
}