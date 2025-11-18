package com.sitco.api.controllers;

import com.sitco.api.dtos.MaterialDto;
import com.sitco.api.entities.Material;
import com.sitco.api.mappers.MaterialMapper;
import com.sitco.api.repositories.MaterialRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/materials")
public class MaterialController{
    MaterialRepository materialrepository;
    MaterialMapper materialMapper;

    @GetMapping
    public List<MaterialDto> getAllMaterials(
            @RequestParam(required = false, name = "materialTypeId") Integer materialTypeId
    ){
        List<Material> materials;
        if(materialTypeId != null){
            materials = materialrepository.findByMaterialTypeId(materialTypeId);

        } else {
            materials = materialrepository.findAll();
        }
        return materials.stream().map(materialMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(
            @PathVariable Long id){
        Material material =  materialrepository.findById(id).orElse(null);
        if(material == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materialMapper.toDto(material));
    }
}
