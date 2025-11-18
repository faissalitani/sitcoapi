package com.sitco.api.mappers;

import com.sitco.api.dtos.MaterialTypeDto;
import com.sitco.api.entities.MaterialType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialTypeMapper {
    MaterialTypeDto toDto(MaterialType materialType);
}
