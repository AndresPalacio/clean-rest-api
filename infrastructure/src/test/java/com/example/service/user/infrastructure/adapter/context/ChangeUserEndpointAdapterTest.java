package com.example.service.user.infrastructure.adapter.context;


import com.example.service.user.domain.dto.SaveUserBodyDto;
import com.example.service.user.domain.dto.UserDto;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.domain.usecase.ChangeExistingUserUseCase;
import com.example.service.user.domain.usecase.DeleteUsersByIdUseCase;
import com.example.service.user.domain.usecase.SubmitNewUserUseCase;
import com.example.service.user.infrastructure.adapter.mapper.UserDtoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.service.user.infrastructure.utils.DataFaker.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ChangeUserEndpointAdapterTest {

    @InjectMocks
    private ChangeUserEndpointAdapter changeUserEndpointAdapter;

    @Mock
    private SubmitNewUserUseCase submitNewUserUseCase;

    @Mock
    private ChangeExistingUserUseCase changeExistingUserUseCase;

    @Mock
    private DeleteUsersByIdUseCase deleteUsersByIdUseCase;

    @Mock
    private UserDtoMapper userDtoMapper;

    @Test
    public void shouldSaveNewUser_returningUserData() {
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User userTransformedFromSaveUserDto = fakeUser();
        User userCreated = fakeUser();
        UserDto userDtoConvertedFromSaveUser = fakeUserDto();

        Mockito.when(userDtoMapper.toDomainFromSaveBody(saveUserBodyDto)).thenReturn(userTransformedFromSaveUserDto);
        Mockito.when(submitNewUserUseCase.saveUser(userTransformedFromSaveUserDto)).thenReturn(userCreated);
        Mockito.when(userDtoMapper.toDto(userCreated)).thenReturn(userDtoConvertedFromSaveUser);

        UserDto savedUserDto = changeUserEndpointAdapter.saveUser(saveUserBodyDto);
        assertThat(savedUserDto).isEqualTo(userDtoConvertedFromSaveUser);
    }

    @Test
    public void shouldUpdateUser_returningUserData() {
        int userId = fakeUserIdAsInt();
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User userTransformedFromSaveUserDto = fakeUser();
        User userCreated = fakeUser();
        UserDto userDtoConvertedFromSaveUser = fakeUserDto();

        Mockito.when(userDtoMapper.toDomainFromSaveBody(userId, saveUserBodyDto)).thenReturn(userTransformedFromSaveUserDto);
        Mockito.when(changeExistingUserUseCase.updateUser(userTransformedFromSaveUserDto)).thenReturn(userCreated);
        Mockito.when(userDtoMapper.toDto(userCreated)).thenReturn(userDtoConvertedFromSaveUser);

        UserDto savedUserDto = changeUserEndpointAdapter.updateUser(userId, saveUserBodyDto);
        assertThat(savedUserDto).isEqualTo(userDtoConvertedFromSaveUser);
    }

    @Test
    public void shouldDeleteUser_returningVoid() {
        UserId userId = fakeUserId();

        changeUserEndpointAdapter.deleteUser(userId.intValue());

        Mockito.verify(deleteUsersByIdUseCase).deleteById(userId);
    }

}