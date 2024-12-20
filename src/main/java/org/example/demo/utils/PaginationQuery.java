package org.example.demo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public class PaginationQuery {

    public static <T> Page<T> executeQuery(EntityManager entityManager, String jpql, Map<String, Object> queryParams, int page, int pageSize, Class<T> entityType) {
        TypedQuery<T> query = entityManager.createQuery(jpql, entityType);

        // Set query parameters
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        // Apply pagination
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);

        // Execute query and get results
        List<T> results = query.getResultList();

        // Count query for total elements
        String countJpql = createCountQuery(jpql);
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);

        // Set query parameters for count query
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            countQuery.setParameter(entry.getKey(), entry.getValue());
        }

        Long totalElements = countQuery.getSingleResult();
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        return new PageImpl<>(results, pageable, totalElements);
    }

    private static String createCountQuery(String jpql) {
        return "SELECT COUNT(*) " + jpql.substring(jpql.toUpperCase().indexOf("FROM"));
    }
}

