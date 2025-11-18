package com.sitco.api.services;

import com.sitco.api.entities.MoistureType;
import com.sitco.api.repositories.MoistureTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MoistureTypeService {
    private final MoistureTypeRepository moistureTypeRepository;

    public void addMoistureType(String name, String description) {
        moistureTypeRepository.save(new MoistureType(name,description));
    }
}
