package com.sitco.api.dtos;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.Map;

@Data
public class AddUpdateCuttingJobRequest {
    private Long id;

    @NotNull(message = "Cutting job requires a customer")
    private Long customerId;

    private Byte deductEdgebandThickness;

    @NotNull(message = "Parts list is required.")
    private Map<String, Object> partsList;

    private Byte statusId;

    private String report;

    private Map<String, Object> history;
}
