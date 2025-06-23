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
        super(id, parentId, navType, navName, navRouteName,
            isFrame == 1, navUrl, navIcon, isCache == 1,
            navComponent, isVisible == 1, navSort, status == 0
        );
    }

}
