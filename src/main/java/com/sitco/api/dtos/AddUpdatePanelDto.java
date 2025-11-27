package com.sitco.api.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddUpdatePanelDto {
    @NotNull(message = "Material is required")
    private Long materialId;

    @NotNull(message = "Height is required.")
    private BigDecimal height;

    @NotNull(message = "Width is required.")
    private BigDecimal width;

    @NotNull(message = "Trim Cut Height is required.")
    private BigDecimal trimCutHeight;

    @NotNull(message = "Trim Cut Width is required.")
    private BigDecimal trimCutWidth;
}
