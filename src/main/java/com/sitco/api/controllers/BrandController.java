package com.sitco.api.controllers;


import com.sitco.api.dtos.BrandDto;
import com.sitco.api.entities.Brand;
import com.sitco.api.mappers.BrandMapper;
import com.sitco.api.repositories.BrandRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    BrandRepository brandrepository;
    BrandMapper brandmapper;

    //CRUD Methods
    @GetMapping
    public ResponseEntity<List<BrandDto>> getBrands(){
        List<Brand> brands = brandrepository.findAll();
        return ResponseEntity.ok().body(brands.stream().map(brandmapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(
            @PathVariable Integer id)
    {
        var brand = findBrandById(id);
        if (brand == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(brandmapper.toDto(brand));
    }

    @PostMapping
    public ResponseEntity<?> createBrand(
            @Valid @RequestBody BrandDto request,
            UriComponentsBuilder uriBuilder
    ){
        if (brandrepository.existsByNameOrFullName(request.getName(), request.getFullName())) {
            return ResponseEntity.badRequest().body(
                    Map.of("brand", "Brand already exists.")
            );
        }
        var brand = brandmapper.toEntity(request);
        brandrepository.save(brand);

        var brandDto = brandmapper.toDto(brand);
        var uri = uriBuilder.path("/brands/{id}").buildAndExpand(brandDto.getId()).toUri();
        return ResponseEntity.created(uri).body(brandDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> updateBrand(
            @PathVariable Integer id,
            @Valid @RequestBody BrandDto brandDto
    ){
        var brand = findBrandById(id);
        if(brand == null){
            return ResponseEntity.notFound().build();
        }
        brandmapper.update(brandDto,brand);
        brandrepository.save(brand);
        brandDto.setId(brand.getId());

        return ResponseEntity.ok(brandDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id){
        var brand = findBrandById(id);
        if(brand == null){
            return ResponseEntity.notFound().build();
        }
        brandrepository.delete(brand);
        return ResponseEntity.noContent().build();
    }

    //Helper Methods
    Brand findBrandById(Integer id){
        return brandrepository.findById(id).orElse(null);
    }
}
