package com.example.space.specification;

import com.example.space.dto.SpaceNavQueryDTO;
import com.example.space.model.SpaceNav;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpaceNavSpecs implements Specification<SpaceNav> {
    // 日志记录器
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(SpaceNavSpecs.class);
    private final SpaceNavQueryDTO queryDTO;

    public SpaceNavSpecs(SpaceNavQueryDTO queryDTO) {
        this.queryDTO = queryDTO;
    }

    @Override
    public Predicate toPredicate(Root<SpaceNav> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // 按 navName 模糊匹配
        if (queryDTO.getNavName() != null && !queryDTO.getNavName().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("navName")), "%" + queryDTO.getNavName().toLowerCase() + "%"));
        }

        // 按 intStatus 精确匹配
        if (queryDTO.getIntStatus() != null && !queryDTO.getIntStatus().isEmpty()) {
            predicates.add(root.get("status").in(queryDTO.getIntStatus()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
