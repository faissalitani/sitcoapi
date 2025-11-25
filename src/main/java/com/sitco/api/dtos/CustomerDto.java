package com.sitco.api.dtos;

import com.sitco.api.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Name is Required.")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Phone is required.")
    @Size(max = 15)
    private String phone;

    @Email(message = "Email must be valid.")
    @Lowercase(message = "email must be lowercase")
    private String email;
}
