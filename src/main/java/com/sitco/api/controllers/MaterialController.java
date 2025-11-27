package com.sitco.api.controllers;

import com.sitco.api.dtos.AddUpdateMaterialRequest;
import com.sitco.api.dtos.MaterialDto;
import com.sitco.api.entities.Material;
import com.sitco.api.mappers.MaterialMapper;
import com.sitco.api.repositories.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/materials")
public class MaterialController{
    private final MaterialTypeRepository materialTypeRepository;
    private final BrandRepository brandRepository;
    private final GrainDirectionRepository grainDirectionRepository;
    private final MoistureTypeRepository moistureTypeRepository;
    MaterialRepository materialrepository;
    MaterialMapper materialMapper;

    //CRUD Methods
    @GetMapping
    public ResponseEntity<List<MaterialDto>> getAllMaterials(
            @RequestParam(required = false, name = "materialTypeId") Integer materialTypeId
    ){
        List<Material> materials;
        if(materialTypeId != null){
            materials = materialrepository.findByMaterialTypeId(materialTypeId);

        } else {
            materials = materialrepository.findAll();
        }
        return ResponseEntity.ok().body(materials.stream().map(materialMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialDto> getMaterialById(
            @PathVariable Long id){
        Material material =  findMaterialById(id);
        if(material == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materialMapper.toDto(material));
    }

    @PostMapping
    public ResponseEntity<?> createMaterial(
            @Valid @RequestBody AddUpdateMaterialRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ){
        var materialType = materialTypeRepository.findById(request.getMaterialTypeId()).orElse(null);
        var brand = brandRepository.findById(request.getBrandId()).orElse(null);
        var grainDirection = grainDirectionRepository.findById(request.getGrainDirectionId()).orElse(null);
        var moistureType = moistureTypeRepository.findById(request.getMoistureTypeId()).orElse(null);
        if(materialType == null || brand == null || grainDirection == null || moistureType == null){
            return ResponseEntity.badRequest().build();
        }

        var match = materialrepository.findExactMatch(
                request.getMaterialTypeId(),
                request.getBrandId(),
                request.getDecorNumber(),
                request.getGrainDirectionId(),
                request.getThickness(),
                request.getMoistureTypeId()
        );

        if(match.isPresent()){
            return ResponseEntity.badRequest().body(
                    Map.of("material", "Material already exists.")
            );
        }


        var material =  materialMapper.toEntity(request);
        material.setMaterialType(materialType);
        material.setBrand(brand);
        material.setGrainDirection(grainDirection);
        material.setMoistureType(moistureType);

        materialrepository.save(material);

        var materialDto = materialMapper.toDto(material);
        var uri = uriComponentsBuilder.path("/materials/{id}").buildAndExpand(materialDto.getId()).toUri();
        return ResponseEntity.created(uri).body(materialDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialDto> updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody AddUpdateMaterialRequest request
    ){
        var material = findMaterialById(id);
        if(material == null){
            return ResponseEntity.notFound().build();
        }

        var materialType = materialTypeRepository.findById(request.getMaterialTypeId()).orElse(null);
        var brand = brandRepository.findById(request.getBrandId()).orElse(null);
        var grainDirection = grainDirectionRepository.findById(request.getGrainDirectionId()).orElse(null);
        var moistureType = moistureTypeRepository.findById(request.getMoistureTypeId()).orElse(null);
        if(materialType == null || brand == null || grainDirection == null || moistureType == null){
            return ResponseEntity.badRequest().build();
        }

        materialMapper.update(request,material);
        material.setMaterialType(materialType);
        material.setBrand(brand);
        material.setGrainDirection(grainDirection);
        material.setMoistureType(moistureType);
        materialrepository.save(material);

        var materialDto = materialMapper.toDto(material);

        return ResponseEntity.ok(materialDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(
            @PathVariable Long id
    ){
        var material = findMaterialById(id);
        if(material == null){
            return ResponseEntity.notFound().build();
        }
        materialrepository.delete(material);
        return ResponseEntity.noContent().build();
    }

    // Helper Methods
    Material findMaterialById(Long id){
        return materialrepository.findById(id).orElse(null);
    }

}
