package com.softz.identity.controller;

import com.softz.dto.ApiResponse;
import com.softz.dto.UserDto;
import com.softz.dto.request.NewUserRequest;
import com.softz.identity.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ApiResponse<UserDto> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        var userDto = userService.createUser(newUserRequest);
        return ApiResponse.<UserDto>builder()
                .result(userDto)
                .build();
    }

    @GetMapping("/users")
    public ApiResponse<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return ApiResponse.<List<UserDto>>builder()
                .result(users)
                .build();
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") String userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/username/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }


}
