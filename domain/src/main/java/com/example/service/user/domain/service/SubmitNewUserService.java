package com.example.service.user.domain.service;

import com.example.service.user.domain.model.User;
import com.example.service.user.domain.port.persistence.ReadUserPort;
import com.example.service.user.domain.port.persistence.WriteUserPort;
import com.example.service.user.domain.usecase.SubmitNewUserUseCase;
import com.example.service.user.domain.validator.ObjectValidator;
import org.springframework.stereotype.Service;

@Service
class SubmitNewUserService implements SubmitNewUserUseCase {

    private final WriteUserPort writeUserPort;

    private final ReadUserPort readUserPort;

    SubmitNewUserService(WriteUserPort writeUserPort,
                         ReadUserPort readUserPort) {
        this.writeUserPort = writeUserPort;
        this.readUserPort = readUserPort;
    }

    @Override
    public User  saveUser(User user) {
        ObjectValidator.validate(user);

        if(readUserPort.existsUserByName(user)) {
            throw new IllegalArgumentException("User duplicated...");
        }
        return writeUserPort.saveNew(user);
    }
}
