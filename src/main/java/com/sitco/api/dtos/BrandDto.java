package com.sitco.api.dtos;

import com.sitco.api.validation.Uppercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BrandDto {
    private Integer id;

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 4)
    @Uppercase(message = "Name must be uppercase.")
    private String name;

    @NotBlank(message = "Full name is required.")
    @Size(max = 45)
    private String fullName;
}
