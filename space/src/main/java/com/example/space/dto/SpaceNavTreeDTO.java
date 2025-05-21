package com.example.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpaceNavTreeDTO {

    @Schema(description = "导航ID")
    private Long id;

    @Schema(description = "父级导航ID")
    private Long parentId;

    @Schema(description = "导航名称")
    private String navName;

    @Schema(description = "导航跳转链接")
    private String navUrl;

    @Schema(description = "导航图标")
    private String navIcon;

    @Schema(description = "子导航信息")
    private List<SpaceNavTreeDTO> children;

    public SpaceNavTreeDTO(Long id, Long parentId, String navName, String navUrl, String navIcon) {
        this.id = id;
        this.parentId = parentId;
        this.navName = navName;
        this.navUrl = navUrl;
        this.navIcon = navIcon;
    }

}
