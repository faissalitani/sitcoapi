package com.sitco.api.mappers;

import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Panel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses =  {MaterialMapper.class})
public interface PanelMapper {
    @Mapping(source = "material", target = "materialDto")
    PanelDto toDto(Panel panel);
}
