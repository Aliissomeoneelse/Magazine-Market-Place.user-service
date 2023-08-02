package com.company.userservice.service;

import com.company.userservice.dto.ErrorDto;
import com.company.userservice.dto.ResponseDto;
import com.company.userservice.dto.UserDto;
import com.company.userservice.mapper.UserMapper;
import com.company.userservice.module.User;
import com.company.userservice.repository.UserRepository;
import com.company.userservice.repository.UserRepositoryImpl;
import com.company.userservice.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final UserRepositoryImpl userRepositoryImpl;
//    private final UserValidation userValidation;

    public ResponseDto<UserDto> create(UserDto dto) {
//        List<ErrorDto> errors = userValidation.validate(dto);
//        if (!errors.isEmpty()) {
//            return ResponseDto.<UserDto>builder()
//                    .message("Validation error")
//                    .data(dto)
//                    .errors(errors)
//                    .code(-2)
//                    .build();
//        }
        try {
            User user = userMapper.toEntity(dto);
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful created!")
                    .data(userMapper.toDto(user))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while saving error :: " + e.getMessage())
                    .code(-3)
                    .build();
        }
    }

    public ResponseDto<UserDto> get(Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found!")
                    .code(-3)
                    .data(null)
                    .build();
        }
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("OK")
                .data(userMapper.toDto(optional.get()))
                .build();
    }

    public boolean getForValidation(Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return true;
        }
        return false;
    }

    public ResponseDto<Page<UserDto>> getAll(Map<String, String> params) {
        Page<UserDto> user = this.userRepositoryImpl
                .getUsers(params)
                .map(this.userMapper::toDto);
        if (user.isEmpty()) {
            return ResponseDto.<Page<UserDto>>builder()
                    .message("This params " + params + " are not found")
                    .build();
        }
        return ResponseDto.<Page<UserDto>>builder()
                .message("Ok")
                .success(true)
                .data(user)
                .build();
    }

    public ResponseDto<UserDto> update(UserDto dto, Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            User user = userMapper.updateUsersFromDto(dto, optional.get());
            user.setId(optional.get().getId());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful updated!")
                    .data(userMapper.toDto(user))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while updating error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }

    public ResponseDto<UserDto> delete(Integer id) {
        Optional<User> optional = userRepository.findByIdAndDeletedAtIsNull(id);
        if (optional.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .message("User is not found")
                    .code(-1)
                    .success(false)
                    .build();
        }
        try {
            User user = optional.get();
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDto.<UserDto>builder()
                    .success(true)
                    .message("User successful deleted!")
                    .data(userMapper.toDto(user))
                    .build();

        } catch (Exception e) {
            return ResponseDto.<UserDto>builder()
                    .message("User while deleting error :: " + e.getMessage())
                    .code(-1)
                    .build();
        }
    }
}
