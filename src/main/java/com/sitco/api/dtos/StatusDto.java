package com.sitco.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StatusDto {
    private Byte id;

    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Description is required.")
    private String description;
}
