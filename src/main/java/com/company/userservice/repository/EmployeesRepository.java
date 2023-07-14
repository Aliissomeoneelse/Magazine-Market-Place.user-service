package com.company.userservice.repository;

import com.company.userservice.module.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    @Query(value = "select * from employees where deleted_at is null and id = :id", nativeQuery = true)
    Optional<Employees> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);
}