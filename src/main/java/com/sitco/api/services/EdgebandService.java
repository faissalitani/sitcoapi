package com.sitco.api.services;

import com.sitco.api.entities.EdgeBand;
import com.sitco.api.repositories.EdgeBandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class EdgebandService {
    private final EdgeBandRepository edgebandRepository;

    public void addEdgeband(String name, BigDecimal thickness, BigDecimal width) {
        edgebandRepository.save(new EdgeBand(name, thickness, width));
    }
}
