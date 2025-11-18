package com.sitco.api.dtos;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MaterialDto {
    private MaterialTypeDto materialTypeDto;
    private BrandDto brandDto;
    private String decorNumber;
    private GrainDirectionDto grainDirectionDto;
    private BigDecimal thickness;
    private MoistureTypeDto moistureTypeDto;
}
