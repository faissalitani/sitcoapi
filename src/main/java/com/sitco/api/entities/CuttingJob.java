package com.sitco.api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cutting_jobs")
public class CuttingJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cutting_job_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "date_created")
    private Instant dateCreated;

    @ColumnDefault("1")
    @Column(name = "deduct_edgeband_thickness")
    private Byte deductEdgebandThickness;

    @Column(name = "parts_list")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> partsList;

    @Column(name = "status", length = 45)
    private String status;

    @Column(name = "report")
    private byte[] report;

    @Column(name = "history")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> history;

    @OneToMany(mappedBy = "cuttingJob")
    private Set<XmlFile> xmlFiles = new HashSet<>();

}