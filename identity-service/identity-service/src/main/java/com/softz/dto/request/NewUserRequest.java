package com.softz.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {

    @Size(min = 3, max = 30, message = "INVALID_USERNAMES")

    private String username;
    private String password;
}