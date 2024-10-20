package com.softz.identity.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserDto {

    private String userId;
    private String username;
    private String email;
    private List<RoleDto> role;
}
