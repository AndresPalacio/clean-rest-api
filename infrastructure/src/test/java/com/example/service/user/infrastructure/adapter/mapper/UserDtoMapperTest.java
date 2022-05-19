package com.example.service.user.infrastructure.adapter.mapper;

import com.example.service.user.domain.dto.SaveUserBodyDto;
import com.example.service.user.domain.dto.UserDto;
import com.example.service.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.service.user.domain.model.UserFunctions.*;
import static com.example.service.user.infrastructure.utils.DataFaker.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserDtoMapperTest {

    @InjectMocks
    private UserDtoMapper userDtoMapper;

    @Test
    public void shouldMapToDtoFromDomain() {
        User user = fakeUser();
        UserDto userDto = userDtoMapper.toDto(user);

        assertThat(userDto.getFirstName()).isEqualTo(userFirstName.apply(user));
        assertThat(userDto.getLastName()).isEqualTo(userLastName.apply(user));
        assertThat(userDto.getPhone()).isEqualTo(userPhoneNumber.apply(user));
    }

    @Test
    public void shouldMapToDomainFromDtoWithoutUserId() {
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();
        User user = userDtoMapper.toDomainFromSaveBody(saveUserBodyDto);

        assertThat(userIdAsInt.apply(user)).isNull();
        assertThat(userFirstName.apply(user)).isEqualTo(saveUserBodyDto.getFirstName());
        assertThat(userLastName.apply(user)).isEqualTo(saveUserBodyDto.getLastName());
        assertThat(userPhoneNumber.apply(user)).isEqualTo(saveUserBodyDto.getPhone());
    }

    @Test
    public void shouldMapToDomainFromDtoWithUserId() {
        Integer userId = fakeUserIdAsInt();
        SaveUserBodyDto saveUserBodyDto = fakeSaveUserBodyDto();

        User user = userDtoMapper.toDomainFromSaveBody(userId, saveUserBodyDto);

        assertThat(userIdAsInt.apply(user)).isEqualTo(userId);
        assertThat(userFirstName.apply(user)).isEqualTo(saveUserBodyDto.getFirstName());
        assertThat(userLastName.apply(user)).isEqualTo(saveUserBodyDto.getLastName());
        assertThat(userPhoneNumber.apply(user)).isEqualTo(saveUserBodyDto.getPhone());
    }
}