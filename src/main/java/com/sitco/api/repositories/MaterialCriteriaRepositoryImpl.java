package com.sitco.api.repositories;

import com.sitco.api.entities.Material;
import com.sitco.api.entities.MaterialType;
import com.sitco.api.entities.MoistureType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MaterialCriteriaRepositoryImpl implements MaterialCriteriaRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Material> findMaterialsByCriteria(MaterialType materialType, String decorNumber, MoistureType moistureType) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Material> cq = cb.createQuery(Material.class);
        Root<Material> root = cq.from(Material.class);

        List<Predicate> predicates = new ArrayList<>();
        if (materialType != null) {
            predicates.add(cb.equal(root.get("materialType"), materialType));
        }
        if (decorNumber != null) {
            predicates.add(cb.like(root.get("decorNumber"), "%" + decorNumber + "%"));
        }
        if (moistureType != null) {
            predicates.add(cb.equal(root.get("moistureType"), moistureType));
        }

        cq.select(root).where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).getResultList();
    }
}
