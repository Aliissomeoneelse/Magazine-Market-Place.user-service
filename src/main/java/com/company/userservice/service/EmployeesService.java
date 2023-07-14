package com.company.userservice.service;

import com.company.userservice.dto.EmployeesDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.mapper.EmployeesMapper;
import com.company.userservice.module.Employees;
import com.company.userservice.repository.EmployeesRepository;
import com.company.userservice.repository.EmployeesRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesMapper employeesMapper;
    private final EmployeesRepository employeesRepository;
    private final EmployeesRepositoryImpl employeesRepositoryImpl;

    public ResponseDto<EmployeesDto> create(EmployeesDto dto) {
        try {
            Employees employees = employeesMapper.toEntity(dto);
            employees.setCreatedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Employee successful created!")
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee while saving error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> get(Integer id) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<EmployeesDto>builder()
                .success(true)
                .message("OK")
                .data(employeesMapper.toDto(optional.get()))
                .build();
    }

    public boolean getForValidation(Integer id) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return true;
        }
        return false;
    }

    public ResponseDto<Page<EmployeesDto>> getAll(Map<String, String> params) {
        Page<EmployeesDto> employees = this.employeesRepositoryImpl
                .getEmployees(params)
                .map(this.employeesMapper::toDto);
        if (employees.isEmpty()) {
            return ResponseDto.<Page<EmployeesDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<EmployeesDto>>builder()
                .message("Ok")
                .success(true)
                .data(employees)
                .build();
    }

    public ResponseDto<EmployeesDto> update(EmployeesDto dto, Integer id) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Employees employees = employeesMapper.updateEmployeesFromDto(dto, optional.get());
            employees.setId(optional.get().getId());
            employees.setUpdatedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Employee successful updated!")
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> delete(Integer id) {
        Optional<Employees> optional = employeesRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        try {
            Employees employees = optional.get();
            employees.setDeletedAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Employee successful deleted!")
                    .data(employeesMapper.toDto(employees))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<EmployeesDto>builder()
                    .message("Employee while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }
}
