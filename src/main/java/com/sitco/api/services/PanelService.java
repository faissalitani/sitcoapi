package com.sitco.api.services;

import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Material;
import com.sitco.api.entities.Panel;
import com.sitco.api.repositories.MaterialRepository;
import com.sitco.api.repositories.PanelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PanelService {
    private final MaterialRepository materialRepository;
    private final PanelRepository panelRepository;

    @Transactional
    public void addPanel(Long materialId,
                         BigDecimal height,
                         BigDecimal width,
                         BigDecimal trimCutHeight,
                         BigDecimal trimCutWidth){
        Material material = materialRepository.findById(materialId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Material ID: " + materialId));

        Panel panel = new Panel(material, height, width, trimCutHeight, trimCutWidth);
        material.getPanels().add(panel);

        materialRepository.save(material);

        panelRepository.save(panel);

    }

    @Transactional
    public void updateTrimCutWidth(Long id, BigDecimal trimCutWidth){
        panelRepository.updateTrimCutWidth(id, trimCutWidth);
    }

    public List<PanelDto> findPanelDimensionsByMaterialId(Long materialId){
        return panelRepository.findPanelDimensionsByMaterialIdJPQL(materialId);
    }


}
