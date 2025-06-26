package com.example.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpaceNavDTO extends BaseSpaceNavDTO {

    @Schema(description = "状态标志，false-正常，true-删除。默认值为false")
    private Boolean status;

    @Schema(description = "父级导航名称")
    private String parentNavName;

    public SpaceNavDTO() {
        super();
    }

    public SpaceNavDTO(
        Long id, Long parentId, String navType, String navName, String navRouteName,
        int isFrame, String navUrl, String navIcon, int isCache, String navComponent,
        int isVisible, int navSort, int status, String parentNavName
    ) {
        super(id, parentId, navType, navName, navRouteName,
            isFrame == 1, navUrl, navIcon, isCache == 1, navComponent,
            isVisible == 1, navSort
        );
        this.status = status == 0;
        this.parentNavName = parentNavName;
    }

}
