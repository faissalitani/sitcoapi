package com.sitco.api.mappers;

import com.sitco.api.dtos.AddUpdateMaterialRequest;
import com.sitco.api.dtos.MaterialDto;
import com.sitco.api.entities.Material;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {
        MaterialTypeMapper.class,
        BrandMapper.class,
        GrainDirectionMapper.class,
        MoistureTypeMapper.class
})
public interface MaterialMapper {
    @Mappings({
            @Mapping(source = "materialType", target = "materialTypeDto"),
            @Mapping(source = "brand", target = "brandDto"),
            @Mapping(source = "grainDirection", target = "grainDirectionDto"),
            @Mapping(source = "moistureType", target = "moistureTypeDto")
    })
    MaterialDto toDto(Material material);

    Material toEntity(AddUpdateMaterialRequest addUpdateMaterialRequest);

    @Mapping(target = "id", ignore = true)
    void update(AddUpdateMaterialRequest request, @MappingTarget Material material);

}
