package com.example.service.user.domain.service;

import com.example.service.user.domain.model.User;
import com.example.service.user.domain.port.persistence.WriteUserPort;
import com.example.service.user.domain.usecase.ChangeExistingUserUseCase;
import com.example.service.user.domain.validator.ObjectValidator;
import org.springframework.stereotype.Service;


@Service
class ChangeExistingUserService implements ChangeExistingUserUseCase {

    private final WriteUserPort writeUserPort;

    public ChangeExistingUserService(WriteUserPort writeUserPort) {
        this.writeUserPort = writeUserPort;
    }

    @Override
    public User updateUser(User user) {
        ObjectValidator.validate(user);

        // excepciones personalizadas
        return writeUserPort.update(user).get();
    }
}
