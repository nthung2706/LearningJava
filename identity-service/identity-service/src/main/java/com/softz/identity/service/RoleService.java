package com.softz.identity.service;

import com.softz.identity.dto.RoleDto;
import com.softz.identity.dto.request.NewRoleRequest;
import com.softz.identity.entity.Permission;
import com.softz.identity.entity.Role;
import com.softz.identity.exception.AppException;
import com.softz.identity.exception.ErrorCode;
import com.softz.identity.mapper.RoleMapper;
import com.softz.identity.repository.RoleRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {

    RoleRepository roleRepository;
    PermissionService permissionService;
    RoleMapper roleMapper;

    public RoleDto createRole(NewRoleRequest request) {
        List<Integer> idList = request.getPermissions();
        List<Permission> permissions = permissionService.getPermissions(idList);

        if (!CollectionUtils.isEmpty(request.getPermissions()) && idList.size() != permissions.size()) {
            List<Integer> invalidPermissions = idList.stream()
            .filter(id -> permissions.stream().noneMatch(permission -> Integer.valueOf(permission.getId()).equals(id)))
            .collect(Collectors.toList());
    
        
        throw new AppException(ErrorCode.INVALID_PERMISSIONS, invalidPermissions.toString());
}

        Role role = roleMapper.toRole(request);
        role.setPermissions(Set.copyOf(permissions));

        return roleMapper.toRoleDto(roleRepository.save(role));
    }
}