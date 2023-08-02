package com.company.userservice.mapper;

import com.company.userservice.client.service.ImageClient;
import com.company.userservice.client.service.LoanerClient;
import com.company.userservice.dto.UserDto;
import com.company.userservice.module.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected ImageClient imageClient;

    @Autowired
    protected LoanerClient loanerClient;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    public abstract User toEntity(UserDto dto);

//    @Mapping(target = "images", expression = "java(imageClient.getImagesByUsersId(user.getId()).getData())")
//    @Mapping(target = "loaners", expression = "java(loanerClient.getLoanersByUserId(user.getId()).getData())")
    public abstract UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract User updateUsersFromDto(UserDto dto, @MappingTarget User user);
}