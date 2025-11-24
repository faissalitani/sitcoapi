package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "xml_files")
public class XmlFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "xml_file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cutting_job_id")
    private CuttingJob cuttingJob;

    @Column(name = "file_name", length = 200)
    private String fileName;

    @Column(name = "file_content")
    private String fileContent;

}