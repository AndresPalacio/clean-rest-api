package com.example.service.user.domain.port.persistence;

import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;

import java.util.Optional;

public interface WriteUserPort {

    User saveNew(User user);

    Optional<User> update(User user);

    void deleteById(UserId userId);
}
