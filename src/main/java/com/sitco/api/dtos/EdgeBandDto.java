package com.sitco.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EdgeBandDto {
    private Byte id;
    @NotBlank(message = "Name is required.")
    private String name;

    @NotNull(message = "Thickness is required.")
    private BigDecimal thickness;

    @NotNull(message = "Width is required")
    private BigDecimal width;
}
