package com.example.service.user.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {

    private final String firstName;

    private final String lastName;

    private final String phone;

}
