package com.sitco.api.services;

import com.sitco.api.entities.Brand;
import com.sitco.api.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    public void addBrand(String name, String fullName){
        brandRepository.save(new Brand(name,fullName));
    }

    public List<Brand> getBrands(){
         Iterable<Brand> brands = brandRepository.findAll();
         return (List<Brand>) brands;
    }

}
