package com.sitco.api.mappers;

import com.sitco.api.dtos.MoistureTypeDto;
import com.sitco.api.entities.MoistureType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MoistureTypeMapper {
    MoistureTypeDto toDto(MoistureType moistureType);
    MoistureType toEntity(MoistureTypeDto moistureTypeDto);

    @Mapping(target = "id", ignore = true )
    void update(MoistureTypeDto moistureTypeDto, @MappingTarget MoistureType moistureType);
}
