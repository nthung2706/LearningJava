package com.softz.dto.request;


import java.time.LocalDate;
import java.util.List;

import com.softz.identity.validator.DobConstraint;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewUserRequest {
    @NotNull(message = "INVALID_NOTNULL")
    @Size(min = 5, max = 30, message = "INVALID_FIELD")
    private String username;

    @Size(min = 5, max = 30, message = "INVALID_FIELD")
    private String password;

    @DobConstraint(min = 18, message = "INVALID_DATE_OF_BIRTH")
    private LocalDate dob;

    @NotNull(message = "INVALID_NOTNULL")
    @Email(message = "INVALID_EMAIL")
    private String email;

    @NotNull(message = "INVALID_NOTNULL")
    private List<Integer> roleIds; 
    
}