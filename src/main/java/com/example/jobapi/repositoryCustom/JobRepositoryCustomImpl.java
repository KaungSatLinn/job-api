package com.example.jobapi.repositoryCustom;

import com.example.jobapi.dto.JobDto;
import com.example.jobapi.model.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;

public class JobRepositoryCustomImpl implements JobRepositoryCustom{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> findJobDataByFilterdVal(JobDto jobDto) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<Job> root = query.from(Job.class);

        //Path<String> emailPath = user.get("email");

        List<Predicate> predicates = new ArrayList<>();
        if(jobDto.getGender()!= null && !jobDto.getGender().isEmpty()){
            Predicate condition = cb.and(cb.equal(root.get("gender"), jobDto.getGender()));
            predicates.add(condition);
        }
        if(jobDto.getFields()!=null && !jobDto.getFields().isEmpty()){
            Selection<Object>[] selections = jobDto.getFields().stream()
                    .map(root::get)
                    .toArray(Selection[]::new);
            query = query.select(cb.array(selections));
                    //.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));

        }
        query.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));


        return entityManager.createQuery(query)
                .getResultList();
    }
}
