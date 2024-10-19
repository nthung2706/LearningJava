package com.softz.identity.service;

import com.softz.identity.dto.UserDto;
import com.softz.identity.dto.request.NewUserRequest;
import com.softz.identity.dto.request.UpdateUserRequest;
import com.softz.identity.entity.Role;
import com.softz.identity.entity.User;
import com.softz.identity.exception.AppException;
import com.softz.identity.exception.ErrorCode;
import com.softz.identity.mapper.UserMapper;
import com.softz.identity.repository.RoleRepository;
import com.softz.identity.repository.UserRepository;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public UserDto createUser(NewUserRequest newUserRequest) {

        Optional<User> existingUser = userRepository.findByEmail(newUserRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new AppException(ErrorCode.EMAIL_EXISTED, newUserRequest.getEmail());
        }

        Set<Integer> uniqueRoleIds = new HashSet<>(newUserRequest.getRoleIds());
        if (uniqueRoleIds.size() != newUserRequest.getRoleIds().size()) {
        throw new AppException(ErrorCode.DUPLICATE_IDS, "");
        }

        List<Integer> invalidRoleIds = uniqueRoleIds.stream()
            .filter(roleId -> !roleRepository.existsById(roleId))
            .collect(Collectors.toList());

            if (!invalidRoleIds.isEmpty()) {
                String invalidIds = invalidRoleIds.stream()
                                                   .map(String::valueOf)
                                                   .collect(Collectors.joining(", "));
                throw new AppException(ErrorCode.INVALID_IDS, 
                    String.format(invalidIds));
            }
        User user = userMapper.toUser(newUserRequest);

        Set<Role> roles = mapRoleIdsToRoles(newUserRequest.getRoleIds());

        user.setRoles(roles); 

        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED, user.getUsername());
        }

        return userMapper.toUserDto(user);
    }

    private Set<Role> mapRoleIdsToRoles(List<Integer> roleIds) {
        return roleIds.stream()
                .map(roleRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    public UserDto getUserById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, userId));
    }

    public UserDto getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, username));
    }

    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .toList();

    }

    public UserDto updateUserWithRoles(String id, UpdateUserRequest request) {

        var user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if(request.getPassword() != null && !request.getPassword().isEmpty())
            user.setPassword(request.getPassword());
        if(request.getDob() != null)
            user.setDob(request.getDob());
        if(request.getEmail() != null && !request.getEmail().isEmpty())
            user.setEmail(request.getEmail());

        if(request.getRoleIds() != null && request.getRoleIds().size() > 0){
            Set<Role> roles = roleRepository.findAllById(request.getRoleIds()).stream().collect(Collectors.toSet());
            user.setRoles(roles);
            userRepository.save(user);
        }
        user = userRepository.save(user);        
        return userMapper.toUserDto(user);
    }
}
