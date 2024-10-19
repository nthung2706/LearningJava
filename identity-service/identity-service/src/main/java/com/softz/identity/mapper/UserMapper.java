package com.softz.identity.mapper;

import java.util.List;
import java.util.Set;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.softz.identity.dto.RoleDto;
import com.softz.identity.dto.UserDto;
import com.softz.identity.dto.request.NewUserRequest;
import com.softz.identity.entity.Role;
import com.softz.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", source = "roles") 
    UserDto toUserDto(User user);

    User toUser(NewUserRequest newUserRequest);

    List<RoleDto> toRoleDtos(Set<Role> roles);

}

