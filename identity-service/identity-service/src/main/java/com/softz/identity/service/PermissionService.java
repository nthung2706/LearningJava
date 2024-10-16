package com.softz.identity.service;

import com.softz.dto.PermissionDto;
import com.softz.dto.request.NewPermissionRequest;
import com.softz.identity.entity.Permission;
import com.softz.identity.exception.AppException;
import com.softz.identity.exception.ErrorCode;
import com.softz.identity.mapper.PermissionMapper;
import com.softz.identity.repository.PermissionRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    public PermissionDto createPermission(NewPermissionRequest newPermissionRequest) {
        Permission permission = permissionMapper.toPermission(newPermissionRequest);
        
        try {
            permission = permissionRepository.save(permission);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED, permission.getName());
        }

        return permissionMapper.toPermissionDto(permission);
    }

    
    public PermissionDto getPermissionByName(String name) {
        return permissionRepository.findByName(name)
                .map(permissionMapper::toPermissionDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, name));
    }
    
    public List<PermissionDto> getPermission() {
        return permissionRepository.findAll()
            .stream()
            .map(permissionMapper::toPermissionDto)
            .toList();

    }
}
