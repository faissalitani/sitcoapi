package com.sitco.api.controllers;

import com.sitco.api.entities.Brand;
import com.sitco.api.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BrandController {
    BrandRepository brandrepository;

    @GetMapping("/brands")
    public Iterable<Brand> getBrands(){
        return  brandrepository.findAll();
    }
}
