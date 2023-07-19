package com.company.userservice.client.service;

import com.company.userservice.client.dto.ImageDto;
import com.company.userservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "file-service", path = "/images")
public interface ImageClient {
    @GetMapping(value = "/get/{id}")
    ResponseDto<ImageDto> getImages(@PathVariable(value = "id") Integer id);

    @GetMapping(value = "/get-images-by-user/{id}")
    ResponseDto<Set<ImageDto>> getImagesByUsersId(@PathVariable("id") Integer id);

}