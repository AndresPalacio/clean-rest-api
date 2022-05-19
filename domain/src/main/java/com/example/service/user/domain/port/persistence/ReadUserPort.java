package com.example.service.user.domain.port.persistence;

import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;

import java.util.List;
import java.util.Optional;

public interface ReadUserPort {

    Boolean existsUserByName(User user);

    Boolean existsUserById(UserId userId);

    Optional<User> fetchById(UserId userId);

    List<User> fetchAll();
}
