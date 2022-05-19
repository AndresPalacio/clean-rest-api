package com.example.service.user.infrastructure.adapter.persistence;

import com.example.service.user.domain.port.persistence.ReadUserPort;
import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.infrastructure.helpers.annotations.Adapter;
import com.example.service.user.infrastructure.persistence.jpa.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.service.user.domain.model.UserFunctions.userFirstName;
import static com.example.service.user.domain.model.UserFunctions.userLastName;

@Adapter
public class ReadUserAdapter implements ReadUserPort {

    private final UserRepository userRepository;

    private final UserJpaMapper userJpaMapper;

    public ReadUserAdapter(UserRepository userRepository, UserJpaMapper userJpaMapper) {
        this.userRepository = userRepository;
        this.userJpaMapper = userJpaMapper;
    }

    @Override
    public Boolean existsUserByName(User user) {
        String firstName = userFirstName.apply(user);
        String lastName = userLastName.apply(user);
        return !userRepository.findByFirstNameAndLastName(firstName, lastName)
                    .isEmpty();
    }

    @Override
    public Boolean existsUserById(UserId userId) {
        Integer userIdAsInt = userId.intValue();
        return userRepository.existsById(userIdAsInt);
    }

    @Override
    public Optional<User> fetchById(UserId userId) {
        return userRepository.findById(userId.intValue())
                .map(userJpaMapper::toDomain);
    }

    @Override
    public List<User> fetchAll() {
        return userRepository.findAll()
                .stream()
                .map(userJpaMapper::toDomain)
                .collect(Collectors.toList());
    }
}
