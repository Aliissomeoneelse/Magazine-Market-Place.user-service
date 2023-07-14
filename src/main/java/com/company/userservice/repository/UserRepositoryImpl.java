package com.company.userservice.repository;

import com.company.userservice.module.User;
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
public class UserRepositoryImpl {
    private final EntityManager entityManager;

    public Page<User> getUsers(Map<String, String> params) {
        int size = 10, page = 0;
        if (params.containsKey("page")) {
            page = Integer.parseInt(params.get("page"));
        }
        if (params.containsKey("size")) {
            size = Integer.parseInt("size");
        }
        String strQuery = "select u from User u where 1=1 ";
        String countQuery = "select count(u.id) from User u where 1=1 ";
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
            stringBuilder.append(" And u.id = :id");
        }
        if (params.containsKey("firstname")) {
            stringBuilder.append(" And u.firstname = :firstname");
        }
        if (params.containsKey("lastname")) {
            stringBuilder.append(" And u.lastname = :lastname");
        }
        if (params.containsKey("middleName")) {
            stringBuilder.append(" And u.middleName = :middleName");
        }
        if (params.containsKey("username")) {
            stringBuilder.append(" And u.username = :username");
        }
        if (params.containsKey("password")) {
            stringBuilder.append(" And u.password = :password");
        }
        if (params.containsKey("enabled")) {
            stringBuilder.append(" And u.enabled = :enabled");
        }
        if (params.containsKey("borrowName")) {
            stringBuilder.append(" And u.borrowName = :borrowName");
        }
        if (params.containsKey("phoneNumber")) {
            stringBuilder.append(" And u.phoneNumber = :phoneNumber");
        }
        if (params.containsKey("passportSeries")) {
            stringBuilder.append(" And u.passportSeries = :passportSeries");
        }
        if (params.containsKey("firstAddress")) {
            stringBuilder.append(" And u.firstAddress = :firstAddress");
        }
        if (params.containsKey("secondAddress")) {
            stringBuilder.append(" And u.secondAddress = :secondAddress");
        }
        if (params.containsKey("monthlyPrice")) {
            stringBuilder.append(" And u.monthlyPrice = :monthlyPrice");
        }
        if (params.containsKey("employeesId")) {
            stringBuilder.append(" And u.employeesId = :employeesId");
        }
        if (params.containsKey("birthdate")) {
            stringBuilder.append(" And u.birthdate = :birthdate");
        }if (params.containsKey("workingTimeStart")) {
            stringBuilder.append(" And u.workingTimeStart = :workingTimeStart");
        }
        if (params.containsKey("workingTimeEnd")) {
            stringBuilder.append(" And u.workingTimeEnd = :workingTimeEnd");
        }
        if (params.containsKey("workingDaysStart")) {
            stringBuilder.append(" And u.workingDaysStart = :workingDaysStart");
        }
        if (params.containsKey("workingDaysEnd")) {
            stringBuilder.append(" And u.workingDaysEnd = :workingDaysEnd");
        }
        return stringBuilder;
    }

    private void setParams(Query query, Map<String, String> params) {
        if (params.containsKey("id")) {
            query.setParameter("id", params.get("id"));
        }
        if (params.containsKey("firstname")) {
            query.setParameter("firstname", params.get("firstname"));
        }
        if (params.containsKey("lastname")) {
            query.setParameter("lastname", params.get("lastname"));
        }
        if (params.containsKey("middleName")) {
            query.setParameter("middleName", params.get("middleName"));
        }
        if (params.containsKey("username")) {
            query.setParameter("username", params.get("username"));
        }
        if (params.containsKey("enabled")) {
            query.setParameter("enabled", params.get("enabled"));
        }
        if (params.containsKey("password")) {
            query.setParameter("password", params.get("password"));
        }
        if (params.containsKey("borrowName")) {
            query.setParameter("borrowName", params.get("borrowName"));
        }
        if (params.containsKey("phoneNumber")) {
            query.setParameter("phoneNumber", params.get("phoneNumber"));
        }
        if (params.containsKey("passportSeries")) {
            query.setParameter("passportSeries", params.get("passportSeries"));
        }
        if (params.containsKey("firstAddress")) {
            query.setParameter("firstAddress", params.get("firstAddress"));
        }
        if (params.containsKey("secondAddress")) {
            query.setParameter("secondAddress", params.get("secondAddress"));
        }
        if (params.containsKey("monthlyPrice")) {
            query.setParameter("monthlyPrice", params.get("monthlyPrice"));
        }
        if (params.containsKey("employeesId")) {
            query.setParameter("employeesId", params.get("employeesId"));
        }
        if (params.containsKey("birthdate")) {
            query.setParameter("birthdate", params.get("birthdate"));
        }
        if (params.containsKey("workingTimeStart")) {
            query.setParameter("workingTimeStart", params.get("workingTimeStart"));
        }
        if (params.containsKey("workingTimeEnd")) {
            query.setParameter("workingTimeEnd", params.get("workingTimeEnd"));
        }
        if (params.containsKey("workingDaysStart")) {
            query.setParameter("workingDaysStart", params.get("workingDaysStart"));
        }
        if (params.containsKey("workingDaysEnd")) {
            query.setParameter("workingDaysEnd", params.get("workingDaysEnd"));
        }
    }
}
