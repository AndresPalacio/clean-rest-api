package com.example.service.user.domain.service;


import com.example.service.user.domain.model.User;
import com.example.service.user.domain.model.UserId;
import com.example.service.user.domain.port.persistence.ReadUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.*;

import static com.example.service.user.utils.DataFaker.fakeUser;
import static com.example.service.user.utils.DataFaker.fakeUserId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class FindUserServiceTest {

    @InjectMocks
    private FindUserService findUserService;

    @Mock
    private ReadUserPort readUserPort;

    @Test
    public void shouldThrowException_whenFindingById_withInvalidUserId() {
        UserId userId = UserId.of(null);

        assertThatThrownBy(() -> findUserService.findById(userId))
                .isInstanceOf(ConstraintViolationException.class);
        Mockito.verify(readUserPort, Mockito.never()).fetchById(Mockito.any());
    }

    @Test
    public void shouldReturnEmptyByIdUser_whenNotFound() {
        UserId userId = fakeUserId();

        Mockito.when(readUserPort.fetchById(userId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findUserService.findById(userId))
                .isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(readUserPort, Mockito.times(1)).fetchById(Mockito.any());
    }

    @Test
    public void shouldReturnUserById_whenFound() {
        UserId userId = fakeUserId();
        User userFromPort = fakeUser();

        Mockito.when(readUserPort.fetchById(userId)).thenReturn(Optional.of(userFromPort));

        User byId = findUserService.findById(userId);
        assertThat(byId).isEqualTo(userFromPort);
        Mockito.verify(readUserPort, Mockito.times(1)).fetchById(Mockito.any());
    }

    @Test
    public void shouldReturnEmpty_whenNoUsersFound() {
        Mockito.when(readUserPort.fetchAll()).thenReturn(Collections.unmodifiableList(null));

        Collection<User> users = findUserService.fetchAllPersisted();
        assertThat(users).isEmpty();
    }

    @Test
    public void shouldReturnUserList_whenUsersPersisted() {
        User user1 = fakeUser();
        User user2 = fakeUser();

        Mockito.when(readUserPort.fetchAll()).thenReturn(Collections.unmodifiableList(Arrays.asList(user1, user2)));

        Collection<User> users = findUserService.fetchAllPersisted();
        assertThat(users).hasSize(2).containsExactlyInAnyOrder(user1, user2);
    }

}