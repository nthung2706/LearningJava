package com.softz.identity.controller;

import com.softz.dto.ApiResponse;
import com.softz.dto.PermissionDto;
import com.softz.dto.request.NewPermissionRequest;
import com.softz.identity.service.PermissionService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @PostMapping("/permission")
    public ApiResponse<PermissionDto> createPermission(@RequestBody @Valid NewPermissionRequest newPermissionRequest) {
        var permissionDto = permissionService.createPermission(newPermissionRequest);
        return ApiResponse.<PermissionDto>builder()
                .result(permissionDto)
                .build();
    }

    @GetMapping("/permission")
    public ApiResponse<List<PermissionDto>> getPermission() {
        List<PermissionDto> permission = permissionService.getPermission();
        return ApiResponse.<List<PermissionDto>>builder()
                .result(permission)
                .build();
    }

    @GetMapping("/permission/{name}")
    public PermissionDto getPermissionByName(@PathVariable("name") String name) {
        return permissionService.getPermissionByName(name);
    }


}
