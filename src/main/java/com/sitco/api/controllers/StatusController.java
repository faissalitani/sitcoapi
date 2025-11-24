package com.sitco.api.controllers;

import com.sitco.api.dtos.StatusDto;
import com.sitco.api.mappers.StatusMapper;
import com.sitco.api.repositories.StatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/statuses")
public class StatusController {
    StatusRepository statusRepository;
    StatusMapper statusMapper;

    @GetMapping
    public ResponseEntity<List<StatusDto>> getAllStatuses() {
        var statuses = statusRepository.findAll();
        return ResponseEntity.ok().body(statuses.stream().map(statusMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatusById(
            @PathVariable Byte id
    ) {
        var status = statusRepository.findById(id).orElse(null);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(statusMapper.toDto(status));
    }
}
