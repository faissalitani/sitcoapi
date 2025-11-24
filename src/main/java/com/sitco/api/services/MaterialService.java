package com.sitco.api.services;

import com.sitco.api.entities.*;
import com.sitco.api.repositories.*;
import com.sitco.api.repositories.specifications.MaterialSpec;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialTypeRepository materialTypeRepository;
    private final BrandRepository brandRepository;
    private final GrainDirectionRepository grainDirectionRepository;
    private final MoistureTypeRepository moistureTypeRepository;

    public void addMaterial(Byte materialTypeId,
                            int brandId,
                            String decorNumber,
                            Byte grainDirectionId,
                            BigDecimal thickness,
                            Byte moistureTypeId) {

        MaterialType materialType = materialTypeRepository.findById(materialTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Material Type ID: " + materialTypeId));

        Brand brand = brandRepository.findById(brandId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Brand ID: " + brandId));

        GrainDirection grainDirection = grainDirectionRepository.findById(grainDirectionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Grain Direction ID: " + grainDirectionId));

        MoistureType moistureType = moistureTypeRepository.findById(moistureTypeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Moisture Type ID: " + moistureTypeId));

        Material material = new Material(
                materialType,
                brand,
                decorNumber,
                grainDirection,
                thickness,
                moistureType
        );

        materialRepository.save(material);

    }

    @Transactional
    public void getMaterials() {
        List<Material> materials = materialRepository.findAll();
        materials.forEach(material -> {
            System.out.println(material.getMaterialType().getName() + material.getBrand().getName()
            + material.getDecorNumber() +  material.getMoistureType().getName());
            System.out.println(material.getPanels());
        });

    }

    //Entity Graph
    @Transactional
    public void getMaterialPanels(Long id){
        var material =  materialRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Material ID"));

        var materialCode = material.getMaterialType().getName()
                + material.getBrand().getName()
                + material.getDecorNumber()
                + material.getMoistureType().getName()
                + material.getThickness().intValue();

        material.getPanels().forEach(panel -> {
            System.out.println(materialCode + panel.getHeight().intValue() + panel.getWidth().intValue());
        });
    }

    // Using a stored procedure
    @Transactional
    public void findByMoistureType(int moistureTypeId) {
        List<Material> materials = materialRepository.findMaterialsByMoistureType(moistureTypeId);
        materials.forEach(material -> {
            System.out.println(material.getId());
        });
    }

    // Query by example.
    @Transactional
    public void fetchMaterials(){
        Material material = new Material();
        material.setDecorNumber("509");

        ExampleMatcher matcher = ExampleMatcher.matching()
                //.withIncludeNullValues()
                //.withIgnorePaths("moistureType")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(material, matcher );
        List<Material> materials = materialRepository.findAll(example);
        materials.forEach(System.out::println);

    }

    // Criteria Query with Criteria interface (and implementation) extended by material Repository
    public void fetchMaterialsByCriteria() {
        var materials = materialRepository.findMaterialsByCriteria(null, "509", null);
        materials.forEach(material -> {
            System.out.println(material.getId());
        });
    }

    public void fetchMaterialsBySpecification(MaterialType materialType, String decorNumber, MoistureType moistureType) {
        Specification<Material> specification = Specification.unrestricted();

        if (materialType != null) {
            specification = specification.and(MaterialSpec.hasMaterialType(materialType));
        }
        if (decorNumber != null) {
            specification = specification.and(MaterialSpec.hasDecorNumber(decorNumber));
        }
        if (moistureType != null) {
            specification = specification.and(MaterialSpec.hasMoistureType(moistureType));
        }

        materialRepository.findAll(specification).forEach(System.out::println);

    }

    // Sorted Query
    public void fetchSortedMaterials(){
        var sort = Sort.by("decorNumber"); // default is ascending use .descending();
        materialRepository.findAll(sort).forEach(System.out::println);
    }

    // Paginated Query
    public void fetchPaginatedMaterials(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page<Material> page =materialRepository.findAll(pageRequest);
        var materials = page.getContent();
        materials.forEach(System.out::println);
        var totalPages = page.getTotalPages();
        System.out.println("Total Pages: " + totalPages);
        var totalElements = page.getTotalElements();
        System.out.println("Total elements: " + totalElements);
    }


}
