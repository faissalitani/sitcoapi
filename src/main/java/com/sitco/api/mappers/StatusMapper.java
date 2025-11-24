package com.sitco.api.mappers;

import com.sitco.api.dtos.StatusDto;
import com.sitco.api.entities.Status;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    StatusDto toDto(Status status);
}
