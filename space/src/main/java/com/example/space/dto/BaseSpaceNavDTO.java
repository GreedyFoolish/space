package com.example.space.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "基础导航信息")
public class BaseSpaceNavDTO {

    @Schema(description = "导航ID")
    private Long id;

    @Schema(description = "父级导航ID")
    private Long parentId;

    @Schema(description = "导航类型，topNavbar-顶部导肮，catalogue-目录，menu-菜单，默认值为menu")
    private String navType = "menu";

    @Schema(description = "导航名称")
    private String navName;

    @Schema(description = "路由名称")
    private String navRouteName;

    @Schema(description = "是否外链，false-非外链，true-外链。默认值为false")
    @JsonProperty("isFrame")
    private boolean isFrame = false;

    @Schema(description = "导航路由")
    private String navUrl;

    @Schema(description = "导航图标")
    private String navIcon;

    @Schema(description = "是否缓存，false-不缓存，true-缓存。默认值为false")
    @JsonProperty("isCache")
    private boolean isCache = false;

    @Schema(description = "导航组件")
    private String navComponent;

    @Schema(description = "显示状态，false-隐藏，true-显示。默认值为true")
    @JsonProperty("isVisible")
    private boolean isVisible = true;

    @Schema(description = "导航排序，数值越小越靠前。默认值为50")
    private int navSort = 50;

    @Schema(description = "状态标志，false-正常，true-删除。默认值为false")
    private boolean status = false;

}
