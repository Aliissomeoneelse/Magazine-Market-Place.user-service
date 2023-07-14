package com.company.userservice.mapper;

import com.company.userservice.dto.UserDto;
import com.company.userservice.module.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserMapper {

    public User toEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setEmployeesId(dto.getEmployeesId());
        user.setFirstname(dto.getFirstname());
        user.setMiddleName(dto.getMiddleName());
        user.setLastname(dto.getLastname());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setBirthdate(dto.getBirthdate());
        user.setEnabled(dto.getEnabled());
        user.setBorrowName(dto.getBorrowName());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setFirstAddress(dto.getFirstAddress());
        user.setSecondAddress(dto.getSecondAddress());
        user.setMonthlyPrice(dto.getMonthlyPrice());
        user.setPassportSeries(dto.getPassportSeries());
        user.setWorkingDaysStart(dto.getWorkingDaysStart());
        user.setWorkingDaysEnd(dto.getWorkingDaysEnd());
        user.setWorkingTimeStart(dto.getWorkingTimeStart());
        user.setWorkingTimeEnd(dto.getWorkingTimeEnd());
        user.setCreatedAt(dto.getCreatedAt());
        user.setUpdatedAt(dto.getUpdatedAt());
        user.setDeletedAt(dto.getDeletedAt());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmployeesId(user.getEmployeesId());
        dto.setFirstname(user.getFirstname());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastname(user.getLastname());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setBirthdate(user.getBirthdate());
        dto.setEnabled(user.getEnabled());
        dto.setBorrowName(user.getBorrowName());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setFirstAddress(user.getFirstAddress());
        dto.setSecondAddress(user.getSecondAddress());
        dto.setMonthlyPrice(user.getMonthlyPrice());
        dto.setPassportSeries(user.getPassportSeries());
        dto.setWorkingDaysStart(user.getWorkingDaysStart());
        dto.setWorkingDaysEnd(user.getWorkingDaysEnd());
        dto.setWorkingTimeStart(user.getWorkingTimeStart());
        dto.setWorkingTimeEnd(user.getWorkingTimeEnd());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setDeletedAt(user.getDeletedAt());
        return dto;
    }

    public User updateUsersFromDto(UserDto dto, User user) {
        if (dto == null) {
            return user;
        }
        if (dto.getId() != null) {
            user.setId(dto.getId());
        }
        if (dto.getFirstname() != null) {
            user.setFirstname(dto.getFirstname());
        }
        if (dto.getMiddleName() != null) {
            user.setMiddleName(dto.getMiddleName());
        }
        if (dto.getLastname() != null) {
            user.setLastname(dto.getLastname());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }
        if (dto.getEnabled() != null) {
            user.setEnabled(dto.getEnabled());
        }
        if (dto.getBorrowName() != null) {
            user.setBorrowName(dto.getBorrowName());
        }
        if (dto.getPhoneNumber() != null) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }
        if (dto.getPassportSeries() != null) {
            user.setPassportSeries(dto.getPassportSeries());
        }
        if (dto.getFirstAddress() != null) {
            user.setFirstAddress(dto.getFirstAddress());
        }
        if (dto.getSecondAddress() != null) {
            user.setSecondAddress(dto.getSecondAddress());
        }
        if (dto.getMonthlyPrice() != null) {
            user.setMonthlyPrice(dto.getMonthlyPrice());
        }
        if (dto.getEmployeesId() != null) {
            user.setEmployeesId(dto.getEmployeesId());
        }
        if (dto.getBirthdate() != null) {
            user.setBirthdate(dto.getBirthdate());
        }
        if (dto.getWorkingTimeStart() != null) {
            user.setWorkingTimeStart(dto.getWorkingTimeStart());
        }
        if (dto.getWorkingTimeEnd() != null) {
            user.setWorkingTimeEnd(dto.getWorkingTimeEnd());
        }
        if (dto.getWorkingDaysStart() != null) {
            user.setWorkingDaysStart(dto.getWorkingDaysStart());
        }
        if (dto.getWorkingDaysEnd() != null) {
            user.setWorkingDaysEnd(dto.getWorkingDaysEnd());
        }
        if (dto.getCreatedAt() != null) {
            user.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getUpdatedAt() != null) {
            user.setUpdatedAt(dto.getUpdatedAt());
        }
        if (dto.getDeletedAt() != null) {
            user.setDeletedAt(dto.getDeletedAt());
        }

        return user;
    }
}