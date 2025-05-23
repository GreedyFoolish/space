package com.example.space.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "space_nav", schema = "space")
@Schema(description = "导航菜单")
public class SpaceNav extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint unsigned not null comment '导航id'")
    @Schema(description = "导航ID")
    private Long id;

    @Column(name = "parent_id", columnDefinition = "bigint unsigned comment '父级导航id'")
    @Schema(description = "父级导航ID")
    private Long parentId;

    @Column(name = "nav_name", length = 64, columnDefinition = "varchar(64) comment '导航名称'")
    @Schema(description = "导航名称")
    private String navName;

    @Column(name = "nav_url", length = 255, columnDefinition = "varchar(255) null comment '导航路由'")
    @Schema(description = "导航路由")
    private String navUrl;

    @Column(name = "nav_icon", length = 255, columnDefinition = "varchar(255) null comment '导航图标'")
    @Schema(description = "导航图标")
    private String navIcon;

    @Column(name = "nav_sort", columnDefinition = "int unsigned not null default 50 comment '导航排序，数值越小越靠前。默认值为50'")
    @Schema(description = "导航排序，数值越小越靠前。默认值为50")
    private int navSort = 50;

    /**
     * 子导航列表
     */
    @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
    private List<SpaceNav> children;

    /**
     * 关联角色权限表
     */
    @OneToMany(mappedBy = "navId")
    private List<SpaceRoleRule> roleRules;

}
