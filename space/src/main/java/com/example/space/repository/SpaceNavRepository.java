package com.example.space.repository;

import com.example.space.dto.SpaceNavDTO;
import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.model.SpaceNav;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceNavRepository extends JpaRepository<SpaceNav, Long>, JpaSpecificationExecutor<SpaceNav> {

    @Query("SELECT NEW com.example.space.dto.SpaceNavTreeDTO(" +
        "sn.id, sn.parentId, sn.navType, sn.navName, sn.navRouteName, " +
        "sn.isFrame, sn.navUrl, sn.navIcon, sn.isCache, sn.navComponent, " +
        "sn.isVisible, sn.navSort, sn.status) " +
        "FROM SpaceNav sn " +
        "LEFT JOIN SpaceRoleRule srr ON srr.navId.id = sn.id " +
        "LEFT JOIN SpaceRole sr ON srr.roleId.id = sr.id " +
        "WHERE sr.id = :roleId")
    List<SpaceNavTreeDTO> findUserNavsByRoleId(@Param("roleId") Long roleId);

    @Query("SELECT NEW com.example.space.dto.SpaceNavDTO(" +
        "sn.id, sn.parentId, sn.navType, sn.navName, sn.navRouteName, " +
        "sn.isFrame, sn.navUrl, sn.navIcon, sn.isCache, sn.navComponent, " +
        "sn.isVisible, sn.navSort, sn.status, psn.navName) " +
        "FROM SpaceNav sn " +
        "LEFT JOIN SpaceNav psn ON psn.id = sn.parentId ")
    Page<SpaceNavDTO> getAllNavs(Specification<SpaceNav> spec, Pageable pageable);

}
