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

    @Column(name = "nav_url", length = 255, columnDefinition = "varchar(255) null comment '导航跳转链接'")
    @Schema(description = "导航跳转链接")
    private String navUrl;

    @Column(name = "nav_icon", length = 255, columnDefinition = "varchar(255) null comment '导航图标'")
    @Schema(description = "导航图标")
    private String navIcon;

    @Column(name = "nav_sort", columnDefinition = "int unsigned default 50 not null comment '导航链接的排序，数据越小排序越靠前'")
    @Schema(description = "导航链接的排序，数据越小排序越靠前", example = "50")
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
