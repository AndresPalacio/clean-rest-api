package com.example.service.user.infrastructure.adapter.entrypoint.api;

import com.example.service.user.domain.dto.SaveUserBodyDto;
import com.example.service.user.domain.dto.UserDto;
import com.example.service.user.infrastructure.adapter.context.ChangeUserEndpointAdapter;
import com.example.service.user.infrastructure.adapter.context.FindUserEndpointAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/users")
class UserController {

    private final ChangeUserEndpointAdapter changeUserEndpointAdapter;

    private final FindUserEndpointAdapter findUserEndpointAdapter;

    UserController(ChangeUserEndpointAdapter changeUserEndpointAdapter,
                   FindUserEndpointAdapter findUserEndpointAdapter) {
        this.changeUserEndpointAdapter = changeUserEndpointAdapter;
        this.findUserEndpointAdapter = findUserEndpointAdapter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto saveUser(@RequestBody @Valid SaveUserBodyDto saveUserBodyDto) {
        return changeUserEndpointAdapter.saveUser(saveUserBodyDto);
    }

    @PutMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable("user_id") int userId,
                              @RequestBody @Valid SaveUserBodyDto saveUserBodyDto) {
        return changeUserEndpointAdapter.updateUser(userId, saveUserBodyDto);
    }

    @GetMapping("/{user_id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto fetchUserById(@PathVariable("user_id") Integer userId) {
        return findUserEndpointAdapter.fetchUserById(userId);
    }

    @DeleteMapping("/{user_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("user_id") Integer userId) {
        changeUserEndpointAdapter.deleteUser(userId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserDto> fetchAllUsers() {
        return findUserEndpointAdapter.fetchAllUsers();
    }

}
