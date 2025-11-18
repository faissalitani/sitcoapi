package com.sitco.api.controllers;

import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Panel;
import com.sitco.api.mappers.PanelMapper;
import com.sitco.api.repositories.PanelRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/panels")
public class PanelController {
    PanelRepository panelRepository;
    PanelMapper panelMapper;

    @GetMapping
    public List<PanelDto> getAllPanels(){
        List<Panel> panels;
        panels = panelRepository.findAll();

        return panels.stream().map(panelMapper::toDto).toList();
    }
}
