package com.sitco.api.mappers;

import com.sitco.api.dtos.AddUpdatePanelDto;
import com.sitco.api.dtos.PanelDto;
import com.sitco.api.entities.Panel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses =  {MaterialMapper.class})
public interface PanelMapper {
    @Mapping(source = "material", target = "materialDto")
    PanelDto toDto(Panel panel);

    Panel toEntity(AddUpdatePanelDto addUpdatePanelDto);

    @Mapping(target = "id", ignore = true)
    void updatePanel(AddUpdatePanelDto addUpdatePanelDto, @MappingTarget Panel panel);
}
