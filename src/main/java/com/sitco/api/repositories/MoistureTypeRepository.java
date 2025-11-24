package com.sitco.api.repositories;

import com.sitco.api.entities.MoistureType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MoistureTypeRepository extends CrudRepository<MoistureType, Byte> {
    List<MoistureType> findAll();
}
