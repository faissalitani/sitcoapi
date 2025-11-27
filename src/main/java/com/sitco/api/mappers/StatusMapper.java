package com.sitco.api.mappers;

import com.sitco.api.dtos.StatusDto;
import com.sitco.api.entities.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto toDto(Status status);
    Status toEntity(StatusDto statusDto);

    @Mapping(target = "id", ignore = true)
    void update(StatusDto statusDto, @MappingTarget Status status);
}
