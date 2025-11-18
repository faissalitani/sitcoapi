package com.sitco.api.repositories;

import com.sitco.api.entities.Material;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialCriteriaRepository, JpaSpecificationExecutor<Material> {

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType", "grainDirection"})
    List<Material> findAll();

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType"})
    List<Material> findByMaterialTypeId(int materialTypeId);

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType"})
    Optional<Material> findById(Long id);

    @Procedure("find_materials_by_moisture_type")
    List<Material> findMaterialsByMoistureType(int moistureTypeId);
}
