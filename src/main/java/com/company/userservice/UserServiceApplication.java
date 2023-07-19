package com.company.userservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
        info = @Info(
                title = "Simple project",
                description = "just for testing",
                contact = @Contact(
                        name = "Khudoyberdiev Alisher",
                        url = "https://drive.google.com/file/d/1CB9br0jPPMQwT49YUOc0CZ83ZEf9b2TO/view?usp=drive_link",
                        email = "aliwulug1307@gmail.com"
                )
        ),
        tags = {@Tag(
                name = "User"
        ), @Tag(
                name = "Employee"
        )}
)
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}