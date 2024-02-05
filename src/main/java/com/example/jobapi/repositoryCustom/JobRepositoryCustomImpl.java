package com.example.jobapi.repositoryCustom;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class JobRepositoryCustomImpl implements JobRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Job> findJobDataByFilterdVal(JobDto jobDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Job> query = cb.createQuery(Job.class);
        Root<Job> root = query.from(Job.class);

        //Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        if(jobDto.getGender()!= null && !jobDto.getGender().isEmpty()){
            Predicate condition = cb.and(cb.equal(root.get("gender"), jobDto.getGender()));
            predicates.add(condition);
        }
        if(jobDto.getFields()!=null && !jobDto.getFields().isEmpty()){
            for (String field : jobDto.getFields()) {

            }
        }

        query.select(root)
                .where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }
}
