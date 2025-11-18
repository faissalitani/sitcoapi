package com.sitco.api.services;

import com.sitco.api.entities.MaterialType;
import com.sitco.api.repositories.MaterialTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MaterialTypeService {
    private final MaterialTypeRepository materialTypeRepository;

    public void addMaterialType(String name, String description) {
        materialTypeRepository.save(new MaterialType(name,description));
    }
}
