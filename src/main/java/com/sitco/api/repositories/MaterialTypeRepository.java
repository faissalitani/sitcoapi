package com.sitco.api.repositories;

import com.sitco.api.entities.MaterialType;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialTypeRepository extends CrudRepository<MaterialType, Byte> {
    List<MaterialType> findAll();

    boolean existsByNameOrDescription(String name, String description);
}
