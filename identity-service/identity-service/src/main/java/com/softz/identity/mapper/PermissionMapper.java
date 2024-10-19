package com.softz.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.softz.dto.PermissionDto;
import com.softz.dto.request.NewPermissionRequest;
import com.softz.dto.request.UpdatePermissionRequest;
import com.softz.identity.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionDto toPermissionDto(Permission permission);

    Permission toPermission(NewPermissionRequest newPermissionRequest);

    void updatePermissionFromDto(NewPermissionRequest updatePermissionRequest, @MappingTarget Permission existingPermission);


}
