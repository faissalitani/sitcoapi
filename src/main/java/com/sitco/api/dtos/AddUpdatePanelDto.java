package com.sitco.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddUpdatePanelDto {
    private Long materialId;
    private BigDecimal height;
    private BigDecimal width;
    private BigDecimal trimCutHeight;
    private BigDecimal trimCutWidth;
}
