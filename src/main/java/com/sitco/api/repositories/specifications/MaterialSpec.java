package com.sitco.api.repositories.specifications;

import com.sitco.api.entities.Material;
import com.sitco.api.entities.MaterialType;
import com.sitco.api.entities.MoistureType;
import org.springframework.data.jpa.domain.Specification;

public class MaterialSpec {
    public static Specification<Material> hasMaterialType(MaterialType materialType) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("materialType"), materialType);
    }

    public static Specification<Material> hasDecorNumber(String decorNumber) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("decorNumber"), "%" + decorNumber + "%");
    }

    public static Specification<Material> hasMoistureType(MoistureType moistureType) {
        return ((root, query, criteriaBuilder) ->  criteriaBuilder.equal(root.get("moistureType"), moistureType));
    }
}
