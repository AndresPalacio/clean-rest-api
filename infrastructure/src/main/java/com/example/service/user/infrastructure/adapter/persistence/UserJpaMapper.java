package com.example.service.user.infrastructure.adapter.persistence;

import com.example.service.user.domain.model.FullName;
import com.example.service.user.domain.model.Phone;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.infrastructure.helpers.annotations.Mapper;
import com.example.service.user.infrastructure.persistence.model.UserData;

import java.time.LocalDateTime;

import static com.example.service.user.domain.model.UserFunctions.*;

@Mapper
class UserJpaMapper {

    UserJpaMapper() {
        super();
    }

    UserData toJpaEntity(User user) {
        return UserData.builder()
                .id(userIdAsInt.apply(user))
                .firstName(userFirstName.apply(user))
                .lastName(userLastName.apply(user))
                .phone(userPhoneNumber.apply(user))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    UserData toJpaEntity(User user, UserData persistedUserData) {
        return persistedUserData.toBuilder()
                .firstName(userFirstName.apply(user))
                .lastName(userLastName.apply(user))
                .phone(userPhoneNumber.apply(user))
                .updatedAt(LocalDateTime.now())
                .build();
    }

    User toDomain(UserData userData) {
        return User.builder()
                .id(UserId.of(userData.getId()))
                .fullName(FullName.of(userData.getFirstName(), null, userData.getLastName()))
                .phone(Phone.of(userData.getPhone()))
                .build();
    }

}
