package com.sitco.api.dtos;

import com.sitco.api.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank (message = "First name is required.")
    @Size(max = 45, message = "First Name must be less than 45 characters")
    private String firstName;

    @NotBlank (message = "Last name is required.")
    @Size(max = 45, message = "Last Name must be less than 45 characters")
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be valid")
    @Lowercase(message = "Email must be in lowercase.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters in length")
    private String password;

    @NotNull(message = "Role is Required")
    private Byte roleId;
}
