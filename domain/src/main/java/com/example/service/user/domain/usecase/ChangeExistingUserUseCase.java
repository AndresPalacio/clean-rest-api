package com.example.service.user.domain.usecase;

import com.example.service.user.domain.model.User;

public interface ChangeExistingUserUseCase {

    User updateUser(User user);
}
