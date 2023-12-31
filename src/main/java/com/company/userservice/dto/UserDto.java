package com.company.userservice.dto;

import com.company.userservice.client.dto.ImageDto;
import com.company.userservice.client.dto.LoanerDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer id;
    @NotBlank(message = "First name cannot be null or empty")
    private String firstname;
    @NotBlank(message = "Last name cannot be null or empty")
    private String lastname;
    @NotBlank(message = "Middle name cannot be null or empty")
    private String middleName;
    private String username;
    private String password;
    private Boolean enabled;
    private String borrowName;
    private String phoneNumber;
    private String passportSeries;
    private String firstAddress;
    private String secondAddress;
    private Double monthlyPrice;

    private Integer employeesId;

//    private Set<ImageDto> images;
//
//    private Set<LoanerDto> loaners;

    private LocalDateTime birthdate;
    private LocalTime workingTimeStart;
    private LocalTime workingTimeEnd;
    private LocalDate workingDaysStart;
    private LocalDate workingDaysEnd;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}