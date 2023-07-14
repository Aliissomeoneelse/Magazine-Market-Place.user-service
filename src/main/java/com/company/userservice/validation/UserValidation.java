package com.company.userservice.validation;

import com.company.userservice.dto.ErrorDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final EmployeesService employeesService;

    public List<ErrorDto> validate(UserDto dto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (employeesService.getForValidation(dto.getId())) {
            errors.add(new ErrorDto("ID", "Employee ID does not exist."));
        }
        return errors;
    }
}
