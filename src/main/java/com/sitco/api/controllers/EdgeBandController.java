package com.sitco.api.controllers;

import com.sitco.api.dtos.EdgebandDto;
import com.sitco.api.entities.EdgeBand;
import com.sitco.api.mappers.EdgeBandMapper;
import com.sitco.api.repositories.EdgebandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/edgebands")
public class EdgeBandController {
    EdgebandRepository  edgebandRepository;
    EdgeBandMapper edgeBandMapper;

    @GetMapping
    public List<EdgebandDto> getAllEdgebands(){
        List<EdgeBand> edgeBands;
        edgeBands = edgebandRepository.findAll();

        return edgeBands.stream().map(edgeBandMapper::toDto).toList();

    }
}
