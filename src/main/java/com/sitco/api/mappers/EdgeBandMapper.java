package com.sitco.api.mappers;

import com.sitco.api.dtos.EdgeBandDto;
import com.sitco.api.entities.Brand;
import com.sitco.api.entities.EdgeBand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EdgeBandMapper {
    EdgeBandDto toDto(EdgeBand edgeBand);

    EdgeBand toEntity(EdgeBandDto edgebandDto);

    @Mapping(target = "id", ignore = true)
    void update(EdgeBandDto edgeBandDto, @MappingTarget EdgeBand edgeBand);
}
