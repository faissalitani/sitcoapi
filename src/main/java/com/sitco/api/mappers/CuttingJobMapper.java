package com.sitco.api.mappers;

import com.sitco.api.dtos.AddUpdateCuttingJobRequest;
import com.sitco.api.dtos.CuttingJobDto;
import com.sitco.api.entities.CuttingJob;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {CustomerMapper.class, StatusMapper.class})
public interface CuttingJobMapper {
    @Mappings({
            @Mapping(source = "customer", target = "customerDto"),
            @Mapping(source = "status", target = "statusDto")
    })
    CuttingJobDto toDto(CuttingJob cuttingJob);

    CuttingJob toEntity(AddUpdateCuttingJobRequest cuttingJobDto);

    @Mapping(target = "id", ignore = true)
    void update(AddUpdateCuttingJobRequest addUpdateCuttingJobRequest,@MappingTarget CuttingJob cuttingJob);
}
