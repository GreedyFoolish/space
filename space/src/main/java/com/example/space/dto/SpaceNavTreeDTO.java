package com.example.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpaceNavTreeDTO extends BaseSpaceNavDTO {

    @Schema(description = "子导航信息")
    private List<SpaceNavTreeDTO> children;

    public SpaceNavTreeDTO(
        Long id, Long parentId, String navType, String navName, String navRouteName,
        int isFrame, String navUrl, String navIcon, int isCache, String navComponent,
        int isVisible, int navSort, int status
    ) {
        super();
        this.setId(id);
        this.setParentId(parentId);
        this.setNavType(navType);
        this.setNavName(navName);
        this.setNavRouteName(navRouteName);
        this.setFrame(isFrame == 1);
        this.setNavUrl(navUrl);
        this.setNavIcon(navIcon);
        this.setCache(isCache == 1);
        this.setNavComponent(navComponent);
        this.setVisible(isVisible == 1);
        this.setNavSort(navSort);
        this.setStatus(status == 0);
    }

}
