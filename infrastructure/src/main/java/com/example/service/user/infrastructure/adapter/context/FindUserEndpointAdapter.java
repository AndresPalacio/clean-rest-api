package com.example.service.user.infrastructure.adapter.context;

import com.example.service.user.domain.dto.UserDto;
import com.example.service.user.domain.port.entrypoint.api.FindUserEndpointPort;
import com.example.service.user.domain.usecase.FindAllUsersUseCase;
import com.example.service.user.domain.usecase.FindUserByIdUseCase;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.infrastructure.adapter.mapper.UserDtoMapper;
import com.example.service.user.infrastructure.helpers.annotations.Adapter;

import java.util.Collection;
import java.util.stream.Collectors;

@Adapter
public class FindUserEndpointAdapter implements FindUserEndpointPort {

    private final FindAllUsersUseCase findAllUsersUseCase;

    private final FindUserByIdUseCase findUserByIdUseCase;

    private final UserDtoMapper userDtoMapper;

    FindUserEndpointAdapter(FindAllUsersUseCase findAllUsersUseCase,
                            FindUserByIdUseCase findUserByIdUseCase,
                            UserDtoMapper userDtoMapper) {
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.findUserByIdUseCase = findUserByIdUseCase;
        this.userDtoMapper = userDtoMapper;
    }

    @Override
    public UserDto fetchUserById(Integer id) {
        UserId userId = UserId.of(id);
        User foundUser = findUserByIdUseCase.findById(userId);
        return userDtoMapper.toDto(foundUser);
    }

    @Override
    public Collection<UserDto> fetchAllUsers() {
        return findAllUsersUseCase.fetchAllPersisted()
                .stream()
                .map(userDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
