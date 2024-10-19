package com.softz.identity.controller;

import com.softz.identity.dto.ApiResponse;
import com.softz.identity.dto.PermissionDto;
import com.softz.identity.dto.request.NewPermissionRequest;
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

    // @GetMapping("/permission/{name}")
    // public PermissionDto getPermissionByName(@PathVariable("name") String name) {
    //     return permissionService.getPermissionByName(name);
    // }

    @PutMapping("/permission/{id}")
    public ApiResponse<PermissionDto> updatePermission(
            @PathVariable("id") Integer id,
            @RequestBody @Valid NewPermissionRequest updatePermissionRequest) {
        PermissionDto updatedPermission = permissionService.updatePermission(id, updatePermissionRequest);
        return ApiResponse.<PermissionDto>builder()
                .result(updatedPermission)
                .build();
    }

    @DeleteMapping("/permission/{id}")
    public ApiResponse<Void> deletePermission(@PathVariable("id") Integer id) {
        permissionService.deletePermission(id);
        return ApiResponse.<Void>builder()
                .message("Permission deleted successfully")
                .build();
    }

}
