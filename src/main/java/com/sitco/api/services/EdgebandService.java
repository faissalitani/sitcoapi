package com.sitco.api.services;

import com.sitco.api.entities.Edgeband;
import com.sitco.api.repositories.EdgebandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class EdgebandService {
    private final EdgebandRepository edgebandRepository;

    public void addEdgeband(String name, BigDecimal thickness, BigDecimal width) {
        edgebandRepository.save(new Edgeband(name, thickness, width));
    }
}
