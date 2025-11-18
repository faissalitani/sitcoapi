package com.sitco.api.mappers;

import com.sitco.api.dtos.GrainDirectionDto;
import com.sitco.api.entities.GrainDirection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GrainDirectionMapper {
    GrainDirectionDto toDto(GrainDirection grainDirection);
}
