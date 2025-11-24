package com.sitco.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EdgeBandDto {
    private Byte id;
    private String name;
    private BigDecimal thickness;
    private BigDecimal width;
}
