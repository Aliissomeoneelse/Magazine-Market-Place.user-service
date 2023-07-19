package com.company.userservice.client.service;

import com.company.userservice.client.dto.LoanerDto;
import com.company.userservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@FeignClient(name = "basket-service", path = "/loaner")
public interface LoanerClient {
    @GetMapping(value = "/get/{id}")
    ResponseDto<LoanerDto> getLoaners(@PathVariable(value = "id") Integer id);

    @GetMapping("/get-loaners-by-user/{id}")
    ResponseDto<Set<LoanerDto>> getLoanersByUserId(@PathVariable("id") Integer id);
}
