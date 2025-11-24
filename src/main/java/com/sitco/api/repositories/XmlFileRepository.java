package com.sitco.api.repositories;

import com.sitco.api.entities.XmlFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XmlFileRepository extends JpaRepository<XmlFile, Long> {
}
