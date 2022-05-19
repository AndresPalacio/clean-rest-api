package com.example.service.user.infrastructure.adapter.mapper;

import com.example.service.user.domain.dto.SaveUserBodyDto;
import com.example.service.user.domain.dto.UserDto;
import com.example.service.user.domain.model.FullName;
import com.example.service.user.domain.model.Phone;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.infrastructure.helpers.annotations.Mapper;

import static com.example.service.user.domain.model.UserFunctions.*;

@Mapper
public class UserDtoMapper {

    UserDtoMapper() {
        super();
    }

    public UserDto toDto(User user) {
        return UserDto.builder()
                .firstName(userFirstName.apply(user))
                .lastName(userLastName.apply(user))
                .phone(userPhoneNumber.apply(user))
                .build();
    }

    public User toDomainFromSaveBody(SaveUserBodyDto saveUserBodyDto) {
        return User.builder()
                .fullName(FullName.of(saveUserBodyDto.getFirstName(), null, saveUserBodyDto.getLastName()))
                .phone(Phone.of(saveUserBodyDto.getPhone()))
                .build();
    }

    public User toDomainFromSaveBody(Integer userId, SaveUserBodyDto saveUserBodyDto) {
        return User.builder()
                .id(UserId.of(userId))
                .fullName(FullName.of(saveUserBodyDto.getFirstName(), null, saveUserBodyDto.getLastName()))
                .phone(Phone.of(saveUserBodyDto.getPhone()))
                .build();
    }
}
