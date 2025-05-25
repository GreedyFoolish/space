package com.example.space.dto;

import com.example.space.model.SpaceUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class SpaceUserDTO {

    @Schema(description = "用户ID")
    private Long id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String userPassword;

    @Schema(description = "角色ID")
    private Long roleId;

    @Schema(description = "上一次登录时间")
    private Date userLastTime;

    public SpaceUserDTO(SpaceUser spaceUser) {
        this.id = spaceUser.getId();
        this.userName = spaceUser.getUserName();
        this.userPassword = spaceUser.getUserPassword();
        this.roleId = spaceUser.getRoleId().getId();
        this.userLastTime = spaceUser.getUserLastTime();
    }

}
