package com.example.space.controller;

import com.example.space.dto.SpaceNavTreeDTO;
import com.example.space.enums.ResponseCodeEnum;
import com.example.space.model.ResponseEntity;
import com.example.space.service.SpaceNavService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/userPermission")
@Tag(name = "用户权限管理", description = "用户权限相关操作接口")
public class UserPermissionController {

    private final SpaceNavService spaceNavService;

    public UserPermissionController(SpaceNavService spaceNavService) {
        this.spaceNavService = spaceNavService;
    }

    @GetMapping("/routerList")
    @Operation(summary = "获取用户路由列表", description = "返回当前用户的路由权限列表")
    @ApiResponse(responseCode = "200", description = "成功返回路由列表")
    public ResponseEntity<List<SpaceNavTreeDTO>> getUserRoutes() {
        List<SpaceNavTreeDTO> routes = spaceNavService.buildNavTree(1L);
        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), routes);
    }

}
