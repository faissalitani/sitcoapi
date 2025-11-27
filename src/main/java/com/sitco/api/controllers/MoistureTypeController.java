package com.sitco.api.controllers;

import com.sitco.api.dtos.MoistureTypeDto;
import com.sitco.api.entities.MoistureType;
import com.sitco.api.mappers.MoistureTypeMapper;
import com.sitco.api.repositories.MoistureTypeRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/moistureTypes")
public class MoistureTypeController {
    MoistureTypeRepository moistureTypeRepository;
    MoistureTypeMapper moistureTypeMapper;

    //CRUD Methods
    @GetMapping
    public ResponseEntity<List<MoistureTypeDto>> getAllMoistureTypes(){
        List<MoistureType> moistureTypes;
        moistureTypes =  moistureTypeRepository.findAll();

        return ResponseEntity.ok().body(moistureTypes.stream().map(moistureTypeMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoistureTypeDto> getMoistureTypeById(
            @PathVariable Byte id)
    {
        var moistureType = findMoistureTypeById(id);
        if(moistureType == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(moistureTypeMapper.toDto(moistureType));
    }

    @PostMapping
    public ResponseEntity<?> createMoistureType(
            @Valid @RequestBody MoistureTypeDto request,
            UriComponentsBuilder uriComponentsBuilder
    ){
        if(moistureTypeRepository.existsByNameOrDescription(request.getName(), request.getDescription()))
        {
            return ResponseEntity.badRequest().body(
                    Map.of("Moisture Type", "Moisture Type already exists.")
            );
        }
        var moistureType = moistureTypeMapper.toEntity(request);
        moistureTypeRepository.save(moistureType);

        var moistureTypeDto = moistureTypeMapper.toDto(moistureType);
        var uri = uriComponentsBuilder.path("/moistureTypes/{id}").buildAndExpand(moistureTypeDto).toUri();
        return ResponseEntity.created(uri).body(moistureTypeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoistureTypeDto> updateMoistureType(
            @PathVariable Byte id,
            @Valid @RequestBody MoistureTypeDto moistureTypeDto
    ){
        var moistureType = findMoistureTypeById(id);
        if(moistureType == null){
            return ResponseEntity.notFound().build();
        }
        moistureTypeMapper.update(moistureTypeDto, moistureType);
        moistureTypeRepository.save(moistureType);
        return ResponseEntity.ok().body(moistureTypeDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoistureType(
            @PathVariable Byte id
    ){
        var moistureType = findMoistureTypeById(id);
        if(moistureType == null){
            return ResponseEntity.notFound().build();
        }
        moistureTypeRepository.delete(moistureType);
        return ResponseEntity.noContent().build();
    }

    //Helper Methods
    MoistureType findMoistureTypeById(Byte id){
        return moistureTypeRepository.findById(id).orElse(null);
    }
}
