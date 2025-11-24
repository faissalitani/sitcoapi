package com.sitco.api.controllers;

import com.sitco.api.dtos.AddUpdatePanelDto;
import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Panel;
import com.sitco.api.mappers.PanelMapper;
import com.sitco.api.repositories.MaterialRepository;
import com.sitco.api.repositories.PanelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/panels")
public class PanelController {
    private final MaterialRepository materialRepository;
    PanelRepository panelRepository;
    PanelMapper panelMapper;

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
    public ResponseEntity<PanelDto> createPanel(
            @RequestBody AddUpdatePanelDto request,
            UriComponentsBuilder uriComponentsBuilder

            ){
        var material = materialRepository.findById(request.getMaterialId()).orElse(null);
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
        var panel = panelRepository.findById(id).orElse(null);
        if(panel == null){
            return ResponseEntity.notFound().build();
        }

        var material = materialRepository.findById(request.getMaterialId()).orElse(null);
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
        var panel = panelRepository.findById(id).orElse(null);
        if(panel == null){
            return ResponseEntity.notFound().build();
        }
        panelRepository.delete(panel);
        return ResponseEntity.noContent().build();
    }
}
