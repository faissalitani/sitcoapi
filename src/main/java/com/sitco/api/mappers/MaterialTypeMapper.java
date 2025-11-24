package com.sitco.api.mappers;

import com.sitco.api.dtos.MaterialTypeDto;
import com.sitco.api.entities.MaterialType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MaterialTypeMapper {

    MaterialTypeDto toDto(MaterialType materialType);

    MaterialType toEntity(MaterialTypeDto materialTypeDto);

    @Mapping(target = "id", ignore = true)
    void update(MaterialTypeDto materialTypeDto, @MappingTarget MaterialType materialType);
}
