package com.sitco.api.controllers;

import com.sitco.api.dtos.AddUpdateCuttingJobRequest;
import com.sitco.api.dtos.CuttingJobDto;
import com.sitco.api.entities.CuttingJob;
import com.sitco.api.mappers.CuttingJobMapper;
import com.sitco.api.repositories.CustomerRepository;
import com.sitco.api.repositories.CuttingJobRepository;
import com.sitco.api.repositories.StatusRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cuttingJobs")
public class CuttingJobController {
    CuttingJobRepository cuttingJobRepository;
    CuttingJobMapper cuttingJobMapper;
    CustomerRepository customerRepository;
    StatusRepository statusRepository;
    @PersistenceContext
    private EntityManager em;

    // CRUD Methods
    @GetMapping
    public ResponseEntity<List<CuttingJobDto>> getAllCuttingJobs() {
        List<CuttingJob> cuttingJobs = (List<CuttingJob>) cuttingJobRepository.findAll();

        return ResponseEntity.ok()
                .body(cuttingJobs.stream().map(cuttingJobMapper::toDto)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuttingJobDto> getCuttingJobById(@PathVariable Long id) {
        var cuttingJob = findCuttingJobById(id);
        if (cuttingJob == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cuttingJobMapper.toDto(cuttingJob));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CuttingJobDto>  createCuttingJob(
            @Valid @RequestBody AddUpdateCuttingJobRequest request,
            UriComponentsBuilder uriBuilder
    ) {
        var customer = customerRepository.findById(request.getCustomerId()).orElse(null);
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        }
        var status = statusRepository.findById(request.getStatusId()).orElse(null);
        if (status == null) {
            return ResponseEntity.badRequest().build();
        }

        var cuttingJob = cuttingJobMapper.toEntity(request);
        cuttingJob.setCustomer(customer);
        cuttingJob.setStatus(status);
        cuttingJobRepository.save(cuttingJob);

        em.refresh(cuttingJob);

        var cuttingJobDto = cuttingJobMapper.toDto(cuttingJob);

        var uri = uriBuilder.path("/cuttingJobs/{id}").buildAndExpand(cuttingJobDto.getId()).toUri();
        return ResponseEntity.created(uri).body(cuttingJobDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<CuttingJobDto> updateCuttingJob(
            @PathVariable Long id,
            @Valid @RequestBody AddUpdateCuttingJobRequest request
    ){
        var cuttingJob = findCuttingJobById(id);
        if (cuttingJob == null) {
            return ResponseEntity.notFound().build();
        }

        var customer = customerRepository.findById(request.getCustomerId()).orElse(null);
        var status = statusRepository.findById(request.getStatusId()).orElse(null);
        if (customer == null || status == null) {
            return ResponseEntity.badRequest().build();
        }

        cuttingJobMapper.update(request,cuttingJob);
        cuttingJob.setCustomer(customer);
        cuttingJob.setStatus(status);
        cuttingJobRepository.save(cuttingJob);

        var cuttingJobDto = cuttingJobMapper.toDto(cuttingJob);

        return ResponseEntity.ok().body(cuttingJobDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuttingJobById(
            @PathVariable Long id){
        var cuttingJob = findCuttingJobById(id);
        if (cuttingJob == null) {
            return ResponseEntity.notFound().build();
        }
        cuttingJobRepository.delete(cuttingJob);
        return ResponseEntity.noContent().build();
    }

    // Helper Methods
    CuttingJob findCuttingJobById(Long id){
        return cuttingJobRepository.findById(id).orElse(null);
    }

}
