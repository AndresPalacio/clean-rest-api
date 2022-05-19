package com.example.service.user.domain.port.entrypoint.api;

import com.example.service.user.domain.dto.UserDto;

import java.util.Collection;

public interface FindUserEndpointPort {

    Collection<UserDto> fetchAllUsers();

    UserDto fetchUserById(Integer userId);
}
