package com.company.userservice.mapper;

import com.company.userservice.dto.EmployeesDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.module.Employees;
import com.company.userservice.module.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmployeesMapper {
    private final UserMapper userMapper;

    protected Set<User> userDtoSetToUserSet(Set<UserDto> set) {
        if (set == null) {
            return null;
        }
        Set<User> set1 = new LinkedHashSet<User>();
        for (UserDto userDto : set) {
            set1.add(userMapper.toEntity(userDto));
        }
        return set1;
    }

    protected Set<UserDto> userSetToUserDtoSet(Set<User> set) {
        if (set == null) {
            return null;
        }

        Set<UserDto> set1 = new LinkedHashSet<UserDto>();
        for (User user : set) {
            set1.add(userMapper.toDto(user));
        }

        return set1;
    }

    public Employees toEntity(EmployeesDto dto) {
        Employees employees = new Employees();
        employees.setId(dto.getId());
        employees.setUsers(userDtoSetToUserSet(dto.getUsers()));
        employees.setCreatedAt(dto.getCreatedAt());
        employees.setUpdatedAt(dto.getUpdatedAt());
        employees.setDeletedAt(dto.getDeletedAt());
        return employees;
    }

    public EmployeesDto toDto(Employees employees) {
        EmployeesDto dto = new EmployeesDto();
        dto.setId(employees.getId());
        dto.setUsers(userSetToUserDtoSet(employees.getUsers()));
        dto.setCreatedAt(employees.getCreatedAt());
        dto.setUpdatedAt(employees.getUpdatedAt());
        dto.setDeletedAt(employees.getDeletedAt());
        return dto;
    }

    public Employees updateEmployeesFromDto(EmployeesDto dto, Employees employees) {
        if (dto == null) {
            return employees;
        }
        if (dto.getId() != null) {
            employees.setId(dto.getId());
        }
        if (employees.getUsers() != null) {
            Set<User> set = userDtoSetToUserSet(dto.getUsers());
            if (set != null) {
                employees.getUsers().clear();
                employees.getUsers().addAll(set);
            }
        } else {
            Set<User> set = userDtoSetToUserSet(dto.getUsers());
            if (set != null) {
                employees.setUsers(set);
            }
        }
        if (dto.getCreatedAt() != null) {
            employees.setCreatedAt(dto.getCreatedAt());
        }
        if (dto.getUpdatedAt() != null) {
            employees.setUpdatedAt(dto.getUpdatedAt());
        }
        if (dto.getDeletedAt() != null) {
            employees.setDeletedAt(dto.getDeletedAt());
        }
        return employees;
    }
}