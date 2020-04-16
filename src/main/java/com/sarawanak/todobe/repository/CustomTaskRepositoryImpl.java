package com.sarawanak.todobe.repository;

import com.sarawanak.todobe.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@EnableJpaRepositories(basePackages = "com.sarawanak.todobe.repository")
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

    @PersistenceContext
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Task> findByCriteria(Integer userId, Integer priority, Integer status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(Task.class);
        Root<Task> taskRoot = query.from(Task.class);

        Path<String> userIdPath = taskRoot.get("userId");
        Path<String> priorityPath = taskRoot.get("priority");
        Path<String> statusPath = taskRoot.get("status");

        List<Predicate> predicates = new ArrayList<>();

        if (userId != null) {
            predicates.add(criteriaBuilder.equal(userIdPath, userId));
        }
        if (priority != null) {
            predicates.add(criteriaBuilder.equal(priorityPath, priority));
        }
        if (status != null) {
            predicates.add(criteriaBuilder.equal(statusPath, status));
        }

        query.select(taskRoot)
            .where(criteriaBuilder
                .and(predicates
                    .toArray(
                        new Predicate[predicates.size()]
                    )
                )
            );
        return entityManager.createQuery(query).getResultList();
    }
}
