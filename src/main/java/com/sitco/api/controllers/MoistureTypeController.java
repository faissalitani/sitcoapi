package com.sitco.api.controllers;

import com.sitco.api.dtos.MoistureTypeDto;
import com.sitco.api.entities.MoistureType;
import com.sitco.api.mappers.MoistureTypeMapper;
import com.sitco.api.repositories.MoistureTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/moistureTypes")
public class MoistureTypeController {
    MoistureTypeRepository moistureTypeRepository;
    MoistureTypeMapper moistureTypeMapper;

    @GetMapping
    public ResponseEntity<List<MoistureTypeDto>> getAllMoistureTypes(){
        List<MoistureType> moistureTypes;
        moistureTypes =  moistureTypeRepository.findAll();

        return ResponseEntity.ok().body(moistureTypes.stream().map(moistureTypeMapper::toDto).toList());
    }
}
