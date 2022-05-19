package com.example.service.user.domain.port.entrypoint.api;

import com.example.service.user.domain.dto.SaveUserBodyDto;
import com.example.service.user.domain.dto.UserDto;

public interface ChangeUserEndpointPort {

    UserDto saveUser(SaveUserBodyDto saveUserBodyDto);

    UserDto updateUser(Integer id, SaveUserBodyDto saveUserBodyDto);

    void deleteUser(Integer userId);

}
