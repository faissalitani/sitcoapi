package com.sitco.api.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Integer roleId;
}
