package com.company.userservice.module;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    @Column(name = "middle_name")
    private String middleName;
    private String lastname;

    private String username;
    private String password;
    private Boolean enabled;

    @Column(name = "borrow_name")
    private String borrowName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "passport_series")
    private String passportSeries;
    @Column(name = "first_address")
    private String firstAddress;
    @Column(name = "second_address")
    private String secondAddress;
    @Column(name = "monthly_price")
    private Double monthlyPrice;

    @Column(name = "employees_id")
    private Integer employeesId;

    private LocalDateTime birthdate;
    @Column(name = "working_time_start")
    private LocalTime workingTimeStart;
    @Column(name = "working_time_end")
    private LocalTime workingTimeEnd;
    @Column(name = "working_days_start")
    private LocalDate workingDaysStart;
    @Column(name = "working_days_end")
    private LocalDate workingDaysEnd;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}
