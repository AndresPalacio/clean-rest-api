package com.example.service.user.domain.service;

import com.example.service.user.domain.port.persistence.ReadUserPort;
import com.example.service.user.domain.port.persistence.WriteUserPort;
import com.example.service.user.domain.usecase.DeleteUsersByIdUseCase;
import com.example.service.user.domain.validator.ObjectValidator;
import com.example.service.user.domain.model.UserId;
import org.springframework.stereotype.Service;

@Service
class DeleteUsersByIdService implements DeleteUsersByIdUseCase {

    private final WriteUserPort writeUserPort;

    private final ReadUserPort readUserPort;

    DeleteUsersByIdService(WriteUserPort writeUserPort,
                           ReadUserPort readUserPort) {
        this.writeUserPort = writeUserPort;
        this.readUserPort = readUserPort;
    }

    @Override
    public void deleteById(UserId userId) {
        ObjectValidator.validate(userId);

        if (!readUserPort.existsUserById(userId)) {
            throw new IllegalArgumentException("User missed on the repository, not able to delete it...");
        }

        writeUserPort.deleteById(userId);
    }
}
