package com.sitco.api.controllers;

import com.sitco.api.dtos.GrainDirectionDto;
import com.sitco.api.entities.GrainDirection;
import com.sitco.api.mappers.GrainDirectionMapper;
import com.sitco.api.repositories.GrainDirectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grainDirections")
public class GrainDirectionController {
    GrainDirectionRepository grainDirectionRepository;
    GrainDirectionMapper grainDirectionMapper;

    @GetMapping
    public List<GrainDirectionDto> getGrainDirections() {
        List<GrainDirection> grainDirections;
        grainDirections = grainDirectionRepository.findAll();
        return grainDirections.stream().map(grainDirectionMapper::toDto).toList();
    }
}
