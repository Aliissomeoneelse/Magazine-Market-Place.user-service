package com.company.userservice.repository;

import com.company.userservice.module.Employees;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class EmployeesRepositoryImpl {

    private final EntityManager entityManager;

    public Page<Employees> getEmployees(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt("size");
        }
        String strQuery = "select e from Employees e where 1=1 ";
        String countQuery = "select count(e.id) from Employees e where 1=1 ";
        StringBuilder buildParam = builderParams(params);
        Query query = entityManager.createQuery(strQuery + buildParam);
        Query queryTwo = entityManager.createQuery(countQuery + buildParam);

        setParams(query, params);
        setParams(queryTwo, params);

        query.setFirstResult(size * page);
        query.setMaxResults(size);
        long totalElement = Long.parseLong(queryTwo.getSingleResult().toString());
        return new PageImpl<>(query.getResultList(), PageRequest.of(page, size), totalElement);
    }

    private StringBuilder builderParams(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        if (params.containsKey("id")) {
            stringBuilder.append(" And e.id = :id");
        }
        if (params.containsKey("users")) {
            stringBuilder.append(" And e.users = :users");
        }
        return stringBuilder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("id", params.get("id"));
        }
        if (params.containsKey("users")) {
            query.setParameter("users", params.get("users"));
        }
    }
}