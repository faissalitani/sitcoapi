package com.sitco.api.controllers;


import com.sitco.api.dtos.EdgeBandDto;
import com.sitco.api.entities.EdgeBand;
import com.sitco.api.mappers.EdgeBandMapper;
import com.sitco.api.repositories.EdgeBandRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/edgeBands")
public class EdgeBandController {
    EdgeBandRepository edgeBandRepository;
    EdgeBandMapper edgeBandMapper;

    @GetMapping
    public ResponseEntity<List<EdgeBandDto>> getAllEdgeBands(){
        List<EdgeBand> edgeBands;
        edgeBands = edgeBandRepository.findAll();

        return ResponseEntity.ok().body(edgeBands.stream().map(edgeBandMapper::toDto).toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<EdgeBandDto> getEdgeBandById(
            @PathVariable Byte id
    ){
        var edgeband = edgeBandRepository.findById(id).orElse(null);
        if(edgeband == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(edgeBandMapper.toDto(edgeband));
    }

    @PostMapping
    public ResponseEntity<EdgeBandDto> createEdgeBand(
            @RequestBody EdgeBandDto request,
            UriComponentsBuilder uriBuilder
    ){
        var edgeBand = edgeBandMapper.toEntity(request);
        edgeBandRepository.save(edgeBand);

        var edgeBandDto = edgeBandMapper.toDto(edgeBand);
        var uri = uriBuilder.path("/brands/{id}").buildAndExpand(edgeBandDto.getId()).toUri();
        return ResponseEntity.created(uri).body(edgeBandDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EdgeBandDto> updateEdgeBand(
            @PathVariable Byte id,
            @RequestBody EdgeBandDto edgeBandDto
    ){
        var edgeband = edgeBandRepository.findById(id).orElse(null);
        if(edgeband == null){
            return ResponseEntity.notFound().build();
        }
        edgeBandMapper.update(edgeBandDto, edgeband);
        edgeBandRepository.save(edgeband);
        edgeBandDto.setId(edgeband.getId());

        return ResponseEntity.ok().body(edgeBandDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEdgeBand(
            @PathVariable Byte id
    ){
        var edgeBand = edgeBandRepository.findById(id).orElse(null);
        if(edgeBand == null){
            return ResponseEntity.notFound().build();
        }
        edgeBandRepository.delete(edgeBand);
        return ResponseEntity.noContent().build();
    }
}
