package com.sitco.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddUpdateMaterialRequest {
    private Byte materialTypeId;
    private Integer brandId;
    private String decorNumber;
    private Byte grainDirectionId;
    private BigDecimal thickness;
    private Byte moistureTypeId;
}
