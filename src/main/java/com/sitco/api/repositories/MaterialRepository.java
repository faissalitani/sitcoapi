package com.sitco.api.repositories;

import com.sitco.api.entities.Material;
//import com.sitco.api.entities.MoistureType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long>, MaterialCriteriaRepository, JpaSpecificationExecutor<Material> {

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType", "grainDirection"})
    List<Material> findAll();

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType", "grainDirection"})
    List<Material> findByMaterialTypeId(int materialTypeId);

    @EntityGraph(attributePaths = {"panels", "materialType", "brand", "moistureType", "grainDirection"})
    Optional<Material> findById(Long id);

    @Query(value = "select * from materials m where" +
            " m.material_type_id = :materialTypeId and" +
            " m.brand_id = :brandId and" +
            " m.decor_number = :decorNumber and" +
            " m.grain_direction_id = :grainDirectionId and" +
            " m.thickness = :thickness and" +
            " m.moisture_type_id = :moistureTypeId", nativeQuery = true)
    Optional<Material> findExactMatch(
            @Param("materialTypeId") Byte materialTypeId,
            @Param("brandId") Integer brandId,
            @Param("decorNumber") String decorNumber,
            @Param("grainDirectionId") Byte grainDirectionId,
            @Param("thickness") BigDecimal thickness,
            @Param("moistureTypeId") Byte moistureTypeId
            );


    //@Procedure("find_materials_by_moisture_type")
    //List<Material> findMaterialsByMoistureType(MoistureType moistureType);





}
