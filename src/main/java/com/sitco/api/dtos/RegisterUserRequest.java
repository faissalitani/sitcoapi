package com.sitco.api.dtos;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Byte roleId;
}
