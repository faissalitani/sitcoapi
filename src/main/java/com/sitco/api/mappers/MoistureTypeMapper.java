package com.sitco.api.mappers;

import com.sitco.api.dtos.MoistureTypeDto;
import com.sitco.api.entities.MoistureType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MoistureTypeMapper {
    MoistureTypeDto toDto(MoistureType moistureType);
}
