package com.example.service.user.domain.service;

import com.example.service.user.domain.port.persistence.ReadUserPort;
import com.example.service.user.domain.usecase.FindAllUsersUseCase;
import com.example.service.user.domain.usecase.FindUserByIdUseCase;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.domain.validator.ObjectValidator;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
class FindUserService implements FindUserByIdUseCase, FindAllUsersUseCase {

    private final ReadUserPort readUserPort;

    FindUserService(ReadUserPort readUserPort) {
        this.readUserPort = readUserPort;
    }

    @Override
    public User findById(UserId userId) {
        ObjectValidator.validate(userId);
        // excepcion personalizada
        return readUserPort.fetchById(userId).get();
    }

    @Override
    public Collection<User> fetchAllPersisted() {
        return readUserPort.fetchAll();
    }

}
