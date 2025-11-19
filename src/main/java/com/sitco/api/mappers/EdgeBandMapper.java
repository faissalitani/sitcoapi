package com.sitco.api.mappers;

import com.sitco.api.dtos.EdgebandDto;
import com.sitco.api.entities.EdgeBand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EdgeBandMapper {
    EdgebandDto toDto(EdgeBand edgeband);
}
