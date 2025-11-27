package com.sitco.api.repositories;

import com.sitco.api.entities.GrainDirection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GrainDirectionRepository extends CrudRepository<GrainDirection, Byte> {
    List<GrainDirection> findAll();

    boolean existsByName(String name);
}
