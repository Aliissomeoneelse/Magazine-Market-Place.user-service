package com.company.userservice.controller;

import com.company.userservice.dto.EmployeesDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.service.EmployeesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;

    @PostMapping("/create")
    @Operation(
            tags = "Employee"
    )
    public ResponseDto<EmployeesDto> create(@RequestBody EmployeesDto dto) {
        return employeesService.create(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    @Operation(
            tags = "Employee"
    )
    public ResponseDto<EmployeesDto> get(@PathVariable("id") Integer id) {
        return employeesService.get(id);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "Employee"
    )
    public ResponseDto<Page<EmployeesDto>> getAll(@RequestParam Map<String, String> params) {
        return employeesService.getAll(params);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(
            tags = "Employee"
    )
    public ResponseDto<EmployeesDto> update(@PathVariable("id") Integer id, @RequestBody EmployeesDto dto) {
        return employeesService.update(dto, id);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    @Operation(
            tags = "Employee"
    )
    public ResponseDto<EmployeesDto> delete(@PathVariable("id") Integer id) {
        return employeesService.delete(id);
    }
}