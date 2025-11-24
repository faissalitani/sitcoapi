package com.sitco.api.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PanelDto {
    private long id;
    MaterialDto materialDto;
    BigDecimal height;
    BigDecimal width;
    BigDecimal trimCutHeight;
    BigDecimal trimCutWidth;
}
