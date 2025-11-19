package com.sitco.api.controllers;

import com.sitco.api.dtos.BrandDto;
import com.sitco.api.entities.Brand;
import com.sitco.api.mappers.BrandMapper;
import com.sitco.api.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/brands")
public class BrandController {
    BrandRepository brandrepository;
    BrandMapper brandmapper;

    @GetMapping
    public List<BrandDto> getBrands(){
        List<Brand> brands = brandrepository.findAll();
        return brands.stream().map(brandmapper::toDto).toList();
    }
}
