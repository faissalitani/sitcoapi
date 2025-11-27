package com.sitco.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class XmlFileDto {
    private Long id;

    @NotNull(message = "Cutting job required.")
    private Long cuttingJobId;

    @NotBlank(message = "File name required.")
    private String fileName;

    @NotBlank(message = "XML content requried.")
    private String fileContent;
}
