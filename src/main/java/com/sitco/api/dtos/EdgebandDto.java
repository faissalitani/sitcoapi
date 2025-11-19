package com.sitco.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EdgebandDto {
    private String name;
    private BigDecimal thickness;
    private BigDecimal width;
}
