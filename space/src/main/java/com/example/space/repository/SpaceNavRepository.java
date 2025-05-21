package com.example.space.repository;

import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.model.SpaceNav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpaceNavRepository extends JpaRepository<SpaceNav, Long> {

    @Query("SELECT NEW com.example.space.dto.SpaceNavTreeDTO(sn.id, sn.parentId, sn.navName, sn.navUrl, sn.navIcon) " +
            "FROM SpaceNav sn " +
            "LEFT JOIN SpaceRoleRule srr ON srr.navId.id = sn.id " +
            "LEFT JOIN SpaceRole sr ON srr.roleId.id = sr.id " +
            "WHERE sr.id = :roleId")
    List<SpaceNavTreeDTO> findUserNavsByRoleId(@Param("roleId") Long roleId);

}
