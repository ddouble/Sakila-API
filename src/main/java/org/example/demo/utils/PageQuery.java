package org.example.demo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public class PageQuery {

    private final EntityManager entityManager;
    private final int page;
    private final int pageSize;

    // Default values
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_LINKS_MAX_COUNT = 5;

    // Constructor with default values
    public PageQuery(EntityManager entityManager) {
        this(entityManager, DEFAULT_PAGE, DEFAULT_PAGE_SIZE);
    }

    // Constructor with custom values
    public PageQuery(EntityManager entityManager, int page, int pageSize) {
        this.entityManager = entityManager;
        this.page = page;
        this.pageSize = pageSize;
    }

    public <T> Page<T> fetch(String jpql, Map<String, Object> queryParams, Class<T> entityType) {
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

        // return Page object
        return new PageImpl<>(results, pageable, totalElements);
    }

    private String createCountQuery(String jpql) {
        return "SELECT COUNT(*) " + jpql.substring(jpql.toUpperCase().indexOf("FROM"));
    }
}

