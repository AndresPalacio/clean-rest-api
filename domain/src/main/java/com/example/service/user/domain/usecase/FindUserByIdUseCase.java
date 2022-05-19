package com.example.service.user.domain.usecase;

import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;

public interface FindUserByIdUseCase {

    User findById(UserId userId);
}
