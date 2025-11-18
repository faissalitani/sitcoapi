package com.sitco.api.services;

import com.sitco.api.entities.GrainDirection;
import com.sitco.api.repositories.GrainDirectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GrainDirectionService {
    private final GrainDirectionRepository grainDirectionRepository;

    public void addGrainDirection(String name, String description) {
        grainDirectionRepository.save(new GrainDirection(name,description));
    }
}
