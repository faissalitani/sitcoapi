package com.sitco.api.dtos;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class CuttingJobDto {
    private Long id;
    private CustomerDto customerDto;
    private Instant dateCreated;
    private Byte deductEdgebandThickness;
    private Map<String, Object> partsList;
    private StatusDto statusDto;
    private String report;
    private Map<String, Object> history;
}
