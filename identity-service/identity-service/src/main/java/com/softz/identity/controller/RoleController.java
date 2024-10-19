package com.softz.identity.controller;

import com.softz.dto.ApiResponse;
import com.softz.dto.RoleDto;
import com.softz.dto.request.NewRoleRequest;
import com.softz.identity.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/roles")
    public ApiResponse<RoleDto> createRole(@RequestBody NewRoleRequest newRoleRequest) {
        var roleDto = roleService.createRole(newRoleRequest);
        return ApiResponse.<RoleDto>builder()
                .result(roleDto)
                .build();
    }

    
}