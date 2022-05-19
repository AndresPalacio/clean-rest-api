package com.example.service.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.example.*","com.example.service.user.infrastructure.*"})
@SpringBootApplication
@EnableConfigurationProperties
public class UserSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSpringBootApplication.class, args);
    }

}
