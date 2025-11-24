package com.sitco.api.mappers;

import com.sitco.api.dtos.XmlFileDto;
import com.sitco.api.entities.XmlFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface XmlFileMapper {

    XmlFileDto toDto(XmlFile xmlFile);

    @Mapping(source = "cuttingJobId", target = "cuttingJob.id")
    XmlFile toEntity(XmlFileDto xmlFileDto);

    @Mapping(target = "id", ignore = true)
    void updateXmlFile(XmlFileDto xmlFileDto, @MappingTarget XmlFile xmlFile);
}
