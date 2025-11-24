package com.sitco.api.dtos;

import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class AddUpdateCuttingJobRequest {
    private Long id;
    private Long customerId;
    private Byte deductEdgebandThickness;
    private Map<String, Object> partsList;
    private Byte statusId;
    private String report;
    private Map<String, Object> history;
}
