package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
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
    @Column(name = "id", columnDefinition = "bigint comment '导航id'")
    @Schema(description = "导航ID")
    private Long id;

    @Schema(description = "角色ID")
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_role_id"))
    private SpaceRole roleId;

    @Schema(description = "导航ID")
    @ManyToOne(optional = false)
    @JoinColumn(name = "nav_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "fk_nav_id"))
    private SpaceNav navId;

}
