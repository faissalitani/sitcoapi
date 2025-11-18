package com.sitco.api.mappers;

import com.sitco.api.dtos.BrandDto;
import com.sitco.api.entities.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    BrandDto toDto(Brand brand);
}
