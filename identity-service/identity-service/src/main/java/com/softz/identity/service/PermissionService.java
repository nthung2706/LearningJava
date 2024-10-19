package com.softz.identity.service;

import com.softz.identity.dto.PermissionDto;
import com.softz.identity.dto.request.NewPermissionRequest;
import com.softz.identity.dto.request.UpdatePermissionRequest;
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
                .findFirst()
                .map(permissionMapper::toPermissionDto)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, name));
    }
    
    public List<PermissionDto> getPermission() {
        return permissionRepository.findAll()
            .stream()
            .map(permissionMapper::toPermissionDto)
            .toList();

    }

    public PermissionDto updatePermission(Integer id, NewPermissionRequest updatePermissionRequest) {
        Permission existingPermission = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, id.toString()));
    
        permissionMapper.updatePermissionFromDto(updatePermissionRequest, existingPermission);
        
        try {
            Permission updatedPermission = permissionRepository.save(existingPermission);
            return permissionMapper.toPermissionDto(updatedPermission);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED, existingPermission.getName());
        }
    }

    
    
    
    
    

    public void deletePermission(Integer id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND, id.toString()));
    
        permissionRepository.delete(permission);
    }



    public List<Permission> getPermissions(List<Integer> permissions) {
        return permissionRepository.findByIdIn(permissions);
    }
    
    
}
