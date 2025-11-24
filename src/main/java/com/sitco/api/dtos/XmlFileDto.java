package com.sitco.api.dtos;

import lombok.Data;

@Data
public class XmlFileDto {
    private Long id;
    private Long cuttingJobId;
    private String fileName;
    private String fileContent;
}
