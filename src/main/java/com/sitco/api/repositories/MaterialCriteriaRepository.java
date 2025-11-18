package com.sitco.api.repositories;

import com.sitco.api.entities.Material;
import com.sitco.api.entities.MaterialType;
import com.sitco.api.entities.MoistureType;

import java.util.List;

public interface MaterialCriteriaRepository {
    List<Material> findMaterialsByCriteria(MaterialType materialType, String decorNumber, MoistureType moistureType);
}
