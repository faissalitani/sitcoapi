package com.sitco.api.controllers;

import com.sitco.api.dtos.MaterialTypeDto;
import com.sitco.api.entities.MaterialType;
import com.sitco.api.mappers.MaterialTypeMapper;
import com.sitco.api.repositories.MaterialTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/materialTypes")
public class MaterialTypeController {
    MaterialTypeRepository materialTypeRepository;
    MaterialTypeMapper materialTypeMapper;

    @GetMapping
    public List<MaterialTypeDto> findAll() {
        List<MaterialType> materialTypes;
        materialTypes = materialTypeRepository.findAll();
        return materialTypes.stream().map(materialTypeMapper::toDto).toList();
    }
}
