package com.softz.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {

    @Size(min = 5, max = 30, message = "INVALID_FIELD")
    private String username;

    @Size(min = 5, max = 30, message = "INVALID_FIELD")
    private String password;
}