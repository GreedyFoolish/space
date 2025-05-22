package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "space_role_rule", schema = "space")
@Schema(description = "角色权限表")
public class SpaceRoleRule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint unsigned not null comment '主键id'")
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "角色ID")
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "spaceRoleRule_spaceRole_id_fk"))
    private SpaceRole roleId;

    @Schema(description = "导航ID")
    @ManyToOne(optional = false)
    @JoinColumn(name = "nav_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "spaceRoleRule_spaceNav_id_fk"))
    private SpaceNav navId;

}
