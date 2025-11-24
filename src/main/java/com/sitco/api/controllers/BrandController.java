package com.sitco.api.controllers;


import com.sitco.api.dtos.BrandDto;
import com.sitco.api.entities.Brand;
import com.sitco.api.mappers.BrandMapper;
import com.sitco.api.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    BrandRepository brandrepository;
    BrandMapper brandmapper;

    @GetMapping
    public ResponseEntity<List<BrandDto>> getBrands(){
        List<Brand> brands = brandrepository.findAll();
        return ResponseEntity.ok().body(brands.stream().map(brandmapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(
            @PathVariable Integer id)
    {
        var brand = brandrepository.findById(id).orElse(null);
        if (brand == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(brandmapper.toDto(brand));
    }

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(
            @RequestBody BrandDto request,
            UriComponentsBuilder uriBuilder
    ){
        var brand = brandmapper.toEntity(request);
        brandrepository.save(brand);

        var brandDto = brandmapper.toDto(brand);
        var uri = uriBuilder.path("/brands/{id}").buildAndExpand(brandDto.getId()).toUri();
        return ResponseEntity.created(uri).body(brandDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandDto> updateBrand(
            @PathVariable Integer id,
            @RequestBody BrandDto brandDto,
            UriComponentsBuilder uriBuilder
    ){
        var brand = brandrepository.findById(id).orElse(null);
        if(brand == null){
            return ResponseEntity.notFound().build();
        }
        brandmapper.update(brandDto,brand);
        brandrepository.save(brand);
        brandDto.setId(brand.getId());


        var uri = uriBuilder.path("/brands/{id}").buildAndExpand(brandDto.getId()).toUri();
        return ResponseEntity.created(uri).body(brandDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id){
        var brand = brandrepository.findById(id).orElse(null);
        if(brand == null){
            return ResponseEntity.notFound().build();
        }
        brandrepository.delete(brand);
        return ResponseEntity.noContent().build();
    }
}
