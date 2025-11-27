package com.sitco.api.dtos;

import com.sitco.api.validation.Uppercase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddUpdateMaterialRequest {
    @NotNull(message = "Material Type is required.")
    private Byte materialTypeId;

    @NotNull(message = "Brand is required.")
    private Integer brandId;

    @NotBlank(message = "Decor Number is required.")
    @Uppercase
    private String decorNumber;

    @NotNull(message = "Grain Direction is required.")
    private Byte grainDirectionId;

    @NotNull(message = "Thickness is required.")
    private BigDecimal thickness;

    @NotNull(message = "Moisture Type is required")
    private Byte moistureTypeId;
}
