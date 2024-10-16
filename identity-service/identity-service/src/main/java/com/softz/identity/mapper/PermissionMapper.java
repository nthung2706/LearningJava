package com.softz.identity.mapper;

import org.mapstruct.Mapper;

import com.softz.dto.PermissionDto;
import com.softz.dto.request.NewPermissionRequest;
import com.softz.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toPermissionDto(Permission permission);

    Permission toPermission(NewPermissionRequest newPermissionRequest);
}
