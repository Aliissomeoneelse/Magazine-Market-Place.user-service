package com.company.userservice.mapper;

import com.company.userservice.dto.EmployeesDto;
import com.company.userservice.module.Employees;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;


@Component
@Mapper(componentModel = "spring")
public abstract class EmployeesMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Employees toEntity(EmployeesDto dto);

    public abstract EmployeesDto toDto(Employees employees);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    public abstract Employees updateEmployeesFromDto(EmployeesDto dto, @MappingTarget Employees employees);
}