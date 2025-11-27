package com.sitco.api.dtos;

import com.sitco.api.validation.Uppercase;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MoistureTypeDto {

    @NotBlank(message = "Name is required.")
    @Uppercase
    private String name;

    @NotBlank(message = "Description is required.")
    private String description;
}
