package com.sitco.api.dtos;

import com.sitco.api.validation.Uppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GrainDirectionDto {
    private Byte id;

    @NotBlank(message = "Name is required.")
    @Uppercase(message = "Name must be uppercase")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;
}
