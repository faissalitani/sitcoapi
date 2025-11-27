package com.sitco.api.dtos;


import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PanelDto {
    private long id;

    MaterialDto materialDto;

    @Size(max = 3660, message = "Panel height cannot be more than 3660")
    BigDecimal height;

    @Size(max = 1830, message = "Panel Width cannot be more than 1830")
    BigDecimal width;

    @Size(max = 10, message = "Trim Cut Height cannot be more than 10")
    BigDecimal trimCutHeight;

    @Size(max = 10, message = "Trim Cut Width cannot be more than 10")
    BigDecimal trimCutWidth;
}
