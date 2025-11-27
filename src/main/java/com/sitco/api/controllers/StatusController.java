package com.sitco.api.controllers;

import com.sitco.api.dtos.StatusDto;
import com.sitco.api.entities.Status;
import com.sitco.api.mappers.StatusMapper;
import com.sitco.api.repositories.StatusRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/statuses")
public class StatusController {
    StatusRepository statusRepository;
    StatusMapper statusMapper;

    //CRUD Methods
    @GetMapping
    public ResponseEntity<List<StatusDto>> getAllStatuses() {
        var statuses = statusRepository.findAll();
        return ResponseEntity.ok().body(statuses.stream().map(statusMapper::toDto).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getStatusById(
            @PathVariable Byte id
    ) {
        var status = findStatusById(id);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(statusMapper.toDto(status));
    }

    @PostMapping
    public ResponseEntity<?> createStatus(
            @Valid @RequestBody StatusDto request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        if (statusRepository.existsByName(request.getName()))
            return ResponseEntity.badRequest().body(
                    Map.of("status", "Status Already Exists")
            );

        var status = statusMapper.toEntity(request);
        statusRepository.save(status);

        var statusDto = statusMapper.toDto(status);
        var uri = uriComponentsBuilder.path("/statuses/{id}").buildAndExpand(statusDto.getId()).toUri();
        return ResponseEntity.created(uri).body(statusDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<StatusDto> updateStatus(
            @PathVariable Byte id,
            @Valid @RequestBody StatusDto statusDto
    ){
        var status = findStatusById(id);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }

        statusMapper.update(statusDto,status);
        statusRepository.save(status);
        statusDto.setId(status.getId());

        return ResponseEntity.ok().body(statusDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(
            @PathVariable Byte id
    ){
        var status = findStatusById(id);
        if (status == null) {
            return ResponseEntity.notFound().build();
        }
        statusRepository.delete(status);
        return ResponseEntity.noContent().build();
    }

    // Helper Methods
    Status findStatusById(Byte id){
        return statusRepository.findById(id).orElse(null);
    }
}
