package com.sitco.api.repositories;

import com.sitco.api.entities.EdgeBand;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EdgeBandRepository extends CrudRepository<EdgeBand, Byte> {
    List<EdgeBand> findAll();

    boolean existsByName(String name);
}
