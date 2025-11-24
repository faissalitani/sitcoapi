package com.sitco.api.controllers;

import com.sitco.api.dtos.GrainDirectionDto;
import com.sitco.api.entities.GrainDirection;
import com.sitco.api.mappers.GrainDirectionMapper;
import com.sitco.api.repositories.GrainDirectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/grainDirections")
public class GrainDirectionController {
    GrainDirectionRepository grainDirectionRepository;
    GrainDirectionMapper grainDirectionMapper;

    @GetMapping
    public ResponseEntity<List<GrainDirectionDto>> getGrainDirections() {
        List<GrainDirection> grainDirections;
        grainDirections = grainDirectionRepository.findAll();

        return ResponseEntity.ok().body(grainDirections.stream().map(grainDirectionMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrainDirectionDto> getGrainDirectionById(
            @PathVariable Byte id
    ){
        var grainDirection = grainDirectionRepository.findById(id).orElse(null);
        if(grainDirection == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(grainDirectionMapper.toDto(grainDirection));
    }

    @PostMapping
    public ResponseEntity<GrainDirectionDto> createGrainDirection(
            @RequestBody GrainDirectionDto request,
            UriComponentsBuilder uriComponentsBuilder

    ){
        var grainDirection = grainDirectionMapper.toEntity(request);
        grainDirectionRepository.save(grainDirection);

        var grainDirectionDto = grainDirectionMapper.toDto(grainDirection);
        var uri = uriComponentsBuilder.path("/grainDirections/{id}").buildAndExpand(grainDirectionDto.getId()).toUri();
        return ResponseEntity.created(uri).body(grainDirectionDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrainDirectionDto> updateGrainDirection(
            @PathVariable Byte id,
            @RequestBody GrainDirectionDto grainDirectionDto
    ){
        var grainDirection = grainDirectionRepository.findById(id).orElse(null);

        if (grainDirection == null) {
            return ResponseEntity.notFound().build();
        }

        grainDirectionMapper.update(grainDirectionDto, grainDirection);
        grainDirectionRepository.save(grainDirection);
        grainDirectionDto.setId(grainDirection.getId());

        return ResponseEntity.ok().body(grainDirectionDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrainDirection(
            @PathVariable Byte id
    ){
        var grainDirection = grainDirectionRepository.findById(id).orElse(null);

        if (grainDirection == null) {
            return ResponseEntity.notFound().build();
        }

        grainDirectionRepository.delete(grainDirection);
        return ResponseEntity.noContent().build();
    }
}
