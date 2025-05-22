package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "space_role", schema = "space")
@Schema(description = "系统角色表")
public class SpaceRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint unsigned not null comment '角色id'")
    @Schema(description = "角色ID")
    private Long id;

    @Column(name = "role_name", length = 32, columnDefinition = "varchar(32) comment '角色名称'")
    @Schema(description = "角色名称")
    private String roleName;

    @Column(name = "auth_code", columnDefinition = "bigint comment '权限编码'")
    @Schema(description = "权限编码")
    private Long authCode;

    /**
     * 关联角色权限表
     */
    @OneToMany(mappedBy = "roleId")
    private List<SpaceRoleRule> roleRules;

}
