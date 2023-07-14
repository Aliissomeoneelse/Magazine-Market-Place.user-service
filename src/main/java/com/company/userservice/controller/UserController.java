package com.company.userservice.controller;

import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @Operation(
            tags = "User"
    )
    public ResponseDto<UserDto> create(@RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @GetMapping(value = ("/get/{id}"))
    @Operation(
            tags = "User"
    )
    public ResponseDto<UserDto> get(@PathVariable("id") Integer id) {
        return userService.get(id);
    }

    @GetMapping(value = ("/get-all"))
    @Operation(
            tags = "User"
    )
    public ResponseDto<Page<UserDto>> getAll(@RequestParam Map<String, String> params) {
        return userService.getAll(params);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(
            tags = "User"
    )
    public ResponseDto<UserDto> update(@PathVariable("id") Integer id, @RequestBody UserDto dto) {
        return userService.update(dto, id);
    }

    @DeleteMapping(value = ("/delete/{id}"))
    @Operation(
            tags = "User"
    )
    public ResponseDto<UserDto> delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }
}
