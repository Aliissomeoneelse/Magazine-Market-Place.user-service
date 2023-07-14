package com.company.userservice.repository;

import com.company.userservice.module.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from users where deleted_at is null and id = :id", nativeQuery = true)
    Optional<User> findByIdAndDeletedAtIsNull(@Param(value = "id") Integer id);
    Optional<User> findByUsernameAndDeletedAtIsNull(String username);
}