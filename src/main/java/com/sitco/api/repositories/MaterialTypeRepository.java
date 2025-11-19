package com.sitco.api.repositories;

import com.sitco.api.entities.MaterialType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialTypeRepository extends CrudRepository<MaterialType, Integer> {
    List<MaterialType> findAll();
}
