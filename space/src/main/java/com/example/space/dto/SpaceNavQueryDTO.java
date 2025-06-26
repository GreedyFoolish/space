package com.example.space.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpaceNavQueryDTO extends BaseSpaceNavDTO {

    @Schema(description = "状态标志列表，true-正常，false-删除。")
    private List<Boolean> status;

    @Schema(description = "状态标志列表，0-正常，1-删除。")
    private List<Integer> intStatus;

    public SpaceNavQueryDTO() {
        super();
    }

}
