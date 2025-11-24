package com.sitco.api.controllers;

import com.sitco.api.dtos.MaterialTypeDto;
import com.sitco.api.entities.MaterialType;
import com.sitco.api.mappers.MaterialTypeMapper;
import com.sitco.api.repositories.MaterialTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/materialTypes")
public class MaterialTypeController {
    MaterialTypeRepository materialTypeRepository;
    MaterialTypeMapper materialTypeMapper;

    @GetMapping
    public ResponseEntity<List<MaterialTypeDto>> findAll() {
        List<MaterialType> materialTypes;
        materialTypes = materialTypeRepository.findAll();
        return ResponseEntity.ok().body(materialTypes.stream().map(materialTypeMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialTypeDto> findById(
            @PathVariable Byte id
    ){
        var materialType = materialTypeRepository.findById(id).orElse(null);
        if(materialType == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(materialTypeMapper.toDto(materialType));
    }

    @PostMapping
    public ResponseEntity<MaterialTypeDto> createMaterialType(
            @RequestBody MaterialTypeDto request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var materialType = materialTypeMapper.toEntity(request);
        materialTypeRepository.save(materialType);

        var materialTypeDto = materialTypeMapper.toDto(materialType);
        var uri  = uriComponentsBuilder.path("/materialTypes/{id}").buildAndExpand(materialTypeDto).toUri();
        return ResponseEntity.created(uri).body(materialTypeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialTypeDto> updateMaterialType(
            @PathVariable Byte id,
            @RequestBody MaterialTypeDto materialTypeDto
    ){
        var materialType = materialTypeRepository.findById(id).orElse(null);
        if (materialType == null) {
            return ResponseEntity.notFound().build();
        }
        materialTypeMapper.update(materialTypeDto, materialType);
        materialTypeRepository.save(materialType);
        materialTypeDto.setId(materialType.getId());
        return ResponseEntity.ok(materialTypeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialType(@PathVariable Byte id){
        var materialType = materialTypeRepository.findById(id).orElse(null);
        if (materialType == null) {
            return ResponseEntity.notFound().build();
        }
        materialTypeRepository.delete(materialType);
        return ResponseEntity.noContent().build();
    }
}
