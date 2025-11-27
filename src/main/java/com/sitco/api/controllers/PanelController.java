package com.sitco.api.controllers;

import com.sitco.api.dtos.AddUpdatePanelDto;
import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Material;
import com.sitco.api.entities.Panel;
import com.sitco.api.mappers.PanelMapper;
import com.sitco.api.repositories.MaterialRepository;
import com.sitco.api.repositories.PanelRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/panels")
public class PanelController {
    private final MaterialRepository materialRepository;
    PanelRepository panelRepository;
    PanelMapper panelMapper;

    //CRUD Methods
    @GetMapping
    public ResponseEntity<List<PanelDto>> getAllPanels(){
        List<Panel> panels;
        panels = panelRepository.findAll();

        return ResponseEntity.ok().body(panels.stream().map(panelMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanelDto> getPanelById(
            @PathVariable Long id
    ){
        var panel = panelRepository.findById(id).orElse(null);
        if(panel == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(panelMapper.toDto(panel));
    }

    @PostMapping
    public ResponseEntity<?> createPanel(
            @Valid @RequestBody AddUpdatePanelDto request,
            UriComponentsBuilder uriComponentsBuilder

            ){
        var match = panelRepository.findExactMatch(
                request.getMaterialId(),
                request.getHeight(),
                request.getWidth()
        );

        if(match.isPresent()){
            return ResponseEntity.badRequest().body(
                    Map.of("panel", "Panel Already Exists")
            );
        }

        var material = findMaterialById(request.getMaterialId());
        if(material == null){
            return ResponseEntity.badRequest().build();
        }

        var panel = panelMapper.toEntity(request);
        panel.setMaterial(material);
        panelRepository.save(panel);

        var panelDto = panelMapper.toDto(panel);
        var uri = uriComponentsBuilder.path("panels/{id}").buildAndExpand(panelDto.getId()).toUri();
        return ResponseEntity.created(uri).body(panelDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PanelDto> updatePanel(
            @PathVariable Long id,
            @RequestBody AddUpdatePanelDto request
    ){
        var panel = findPanelById(id);
        if(panel == null){
            return ResponseEntity.notFound().build();
        }

        var material = findMaterialById(request.getMaterialId());
        if(material == null){
            return ResponseEntity.badRequest().build();
        }

        panelMapper.updatePanel(request,panel);
        panel.setMaterial(material);
        panelRepository.save(panel);

        var panelDto = panelMapper.toDto(panel);
        panelDto.setId(panel.getId());

        return ResponseEntity.ok(panelDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanel(
            @PathVariable Long id
    ){
        var panel = findPanelById(id);
        if(panel == null){
            return ResponseEntity.notFound().build();
        }
        panelRepository.delete(panel);
        return ResponseEntity.noContent().build();
    }

    // Helper Methods
    Panel findPanelById(Long id){
        return panelRepository.findById(id).orElse(null);
    }

    Material findMaterialById(Long id){
        return materialRepository.findById(id).orElse(null);
    }
}
