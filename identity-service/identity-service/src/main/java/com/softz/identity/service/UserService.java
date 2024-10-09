package com.softz.identity.service;

import com.softz.dto.UserDto;
import com.softz.dto.request.NewUserRequest;
import com.softz.identity.entity.User;
import com.softz.identity.mapper.UserMapper;
import com.softz.identity.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    public UserDto createUser(NewUserRequest newUserRequest) {
        User user = userMapper.toUser(newUserRequest);
        user = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(user);

        return userDto;
    }

    public UserDto getUserById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public List<UserDto> getUsers() {
        return userRepository.findAll()
            .stream()
            .map(userMapper::toUserDto)
            .toList();

    }
}
