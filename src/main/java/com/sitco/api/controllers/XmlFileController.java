package com.sitco.api.controllers;

import com.sitco.api.dtos.XmlFileDto;
import com.sitco.api.entities.XmlFile;
import com.sitco.api.mappers.XmlFileMapper;
import com.sitco.api.repositories.CuttingJobRepository;
import com.sitco.api.repositories.XmlFileRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/xmlFiles")
public class XmlFileController {
    XmlFileRepository xmlFileRepository;
    XmlFileMapper xmlFileMapper;
    CuttingJobRepository cuttingJobRepository;

    @GetMapping
    public ResponseEntity<List<XmlFileDto>> getAllXmlFiles() {
        var xmlFiles =  xmlFileRepository.findAll();

        return ResponseEntity.ok()
                .body(xmlFiles.stream().map(xmlFileMapper::toDto)
                        .toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<XmlFileDto> getXmlFileById(
            @PathVariable Long id
    ) {
        XmlFile xmlFile = xmlFileRepository.findById(id).orElse(null);
        if (xmlFile == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .body(xmlFileMapper.toDto(xmlFile));
    }

    @PostMapping
    public ResponseEntity<XmlFileDto> createXmlFile(
            @RequestBody XmlFileDto request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var cuttingJob = cuttingJobRepository.findById(request.getCuttingJobId()).orElse(null);
        if (cuttingJob == null) {
            return ResponseEntity.badRequest().build();
        }
        var xmlFile =  xmlFileMapper.toEntity(request);
        xmlFile.setCuttingJob(cuttingJob);
        xmlFileRepository.save(xmlFile);

        var xmlFileDto = xmlFileMapper.toDto(xmlFile);
        xmlFileDto.setCuttingJobId(cuttingJob.getId());
        var uri = uriComponentsBuilder.path("/xmlFiles/{id}").buildAndExpand(xmlFileDto.getId()).toUri();
        return ResponseEntity.created(uri).body(xmlFileDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<XmlFileDto> updateXmlFile(
            @PathVariable Long id,
            @RequestBody XmlFileDto xmlFileDto
    ){
        var xmlFile = xmlFileRepository.findById(id).orElse(null);
        if (xmlFile == null) {
            return ResponseEntity.notFound().build();
        }
        var cuttingJob = cuttingJobRepository.findById(xmlFileDto.getCuttingJobId()).orElse(null);
        if (cuttingJob == null) {
            return ResponseEntity.badRequest().build();
        }

        xmlFileMapper.updateXmlFile(xmlFileDto, xmlFile);
        xmlFile.setCuttingJob(cuttingJob);
        xmlFileRepository.save(xmlFile);

        xmlFileDto.setId(xmlFile.getId());

        return ResponseEntity.ok().body(xmlFileDto);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteXmlFile(
            @PathVariable Long id
    ){
        var xmlFile = xmlFileRepository.findById(id).orElse(null);
        if (xmlFile == null) {
            return ResponseEntity.notFound().build();
        }
        xmlFileRepository.delete(xmlFile);
        return ResponseEntity.noContent().build();
    }
}
