package com.sitco.api.repositories;

import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Panel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PanelRepository extends CrudRepository<Panel,Long> {

    @EntityGraph(attributePaths = "material")
    List<Panel> findAll();

    List<Panel> findByMaterialId(Long materialId);

    @Query(value = "select * from panels p where p.width between :min and :max", nativeQuery = true)
    List<Panel> findPanelsSQL(@Param("min")BigDecimal min, @Param("max") BigDecimal max);

    @Query(value = "select * from panels p where" +
            " p.material_id = :materialId and" +
            " p.height = :height and" +
            " p.width = :width", nativeQuery = true)
    Optional<Panel> findExactMatch(
            @Param("materialId") Long materialId,
            @Param("height") BigDecimal height,
            @Param("width") BigDecimal width);


    @Query("select count(*) from Panel p where p.width between :min and :max")
    int findPanelsCount(@Param("min")BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query(value = "update panels p set  p.trim_cut_width = :trimCutWidth where p.panel_id = :id", nativeQuery = true)
    void updateTrimCutWidth(@Param("id") Long id, @Param("trimCutWidth") BigDecimal trimCutWidth);

    //List<PanelDimensions> findPanelDimensionsByMaterialId(Long materialId);

    @Query("select p.height, p.width from Panel p where p.material.id = :materialId")
    List<PanelDto> findPanelDimensionsByMaterialIdJPQL(@Param("materialId") Long materialId);

}
