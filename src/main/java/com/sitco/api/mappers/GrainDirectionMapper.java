package com.sitco.api.mappers;

import com.sitco.api.dtos.GrainDirectionDto;
import com.sitco.api.entities.GrainDirection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GrainDirectionMapper {
    GrainDirectionDto toDto(GrainDirection grainDirection);
    GrainDirection toEntity(GrainDirectionDto grainDirectionDto);

    @Mapping(target = "id", ignore = true)
    void update(GrainDirectionDto grainDirectionDto, @MappingTarget GrainDirection grainDirection);
}
