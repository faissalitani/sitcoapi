package com.sitco.api.mappers;

import com.sitco.api.dtos.BrandDto;
import com.sitco.api.entities.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandDto toDto(Brand brand);

    Brand toEntity(BrandDto brandDto);

    @Mapping(target = "id", ignore = true)
    void update(BrandDto brandDto, @MappingTarget Brand brand);
}
