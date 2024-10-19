package com.softz.identity.controller;

import com.softz.identity.dto.ApiResponse;
import com.softz.identity.dto.UserDto;
import com.softz.identity.dto.request.NewUserRequest;
import com.softz.identity.dto.request.UpdateUserRequest;
import com.softz.identity.exception.AppException;
import com.softz.identity.exception.ErrorCode;
import com.softz.identity.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody @Valid NewUserRequest newUserRequest) {
        if (newUserRequest.getRoleIds() == null || newUserRequest.getRoleIds().isEmpty()) {
            throw new AppException(ErrorCode.INVALID_ROLE);

        }

        var userDto = userService.createUser(newUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<UserDto>builder().result(userDto).build());
    }

    @GetMapping("/users")
    public ApiResponse<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getUsers();
        return ApiResponse.<List<UserDto>>builder()
                .result(users)
                .build();
    }

    @GetMapping("/user/{id}")
    public ApiResponse<UserDto> getUserById(@PathVariable("id") String userId) {
        UserDto userDto = userService.getUserById(userId);
        return ApiResponse.<UserDto>builder()
                .result(userDto)
                .build();
    }
    
    @GetMapping("/username/{username}")
    public ApiResponse<UserDto> getUserByUsername(@PathVariable("username") String username) {
        UserDto userDto = userService.getUserByUsername(username);
        return ApiResponse.<UserDto>builder()
                .result(userDto)
                .build();
    }
    
    @PutMapping("user/{id}")
    public ApiResponse<UserDto> putUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
        UserDto userDto = userService.updateUserWithRoles(id, request);
        return ApiResponse.<UserDto>builder()
                .result(userDto)
                .build();
    }
    

}
