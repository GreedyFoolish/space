package com.example.space.controller;

import com.example.space.dto.PageResponse;
import com.example.space.dto.SpaceNavDTO;
import com.example.space.dto.SpaceNavQueryDTO;
import com.example.space.enums.ResponseCodeEnum;
import com.example.space.model.ResponseEntity;
import com.example.space.service.SpaceNavService;
import com.example.space.util.RequestValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
@Tag(name = "导航管理", description = "导航相关操作接口")
public class NavController {
    // 日志记录器
    private static final Logger logger = LoggerFactory.getLogger(NavController.class);
    private final SpaceNavService spaceNavService;
    private final RequestValidator requestValidator;

    public NavController(SpaceNavService spaceNavService, RequestValidator requestValidator) {
        this.spaceNavService = spaceNavService;
        this.requestValidator = requestValidator;
    }

    @GetMapping("/list")
    @Operation(summary = "根据名称和状态查询导航", description = "根据提供的导航名称和状态筛选导航列表")
    @ApiResponse(responseCode = "200", description = "成功返回筛选后的导航列表")
    public ResponseEntity<PageResponse<SpaceNavDTO>> getNavList(
        @Valid SpaceNavQueryDTO spaceNavQueryDTO,
        @RequestParam(required = false) Integer pageIndex,
        @RequestParam(required = false) Integer pageSize,
        @RequestAttribute("userId") Long userId
    ) {
        pageIndex = requestValidator.validatePageIndex(pageIndex, null);
        pageSize = requestValidator.validatePageSize(pageSize, null, null);
        String navName = spaceNavQueryDTO.getNavName();
        List<Boolean> status = spaceNavQueryDTO.getStatus();
        // 记录搜索导航列表的参数
        logger.info("搜索导航列表，名称：{}，状态：{}，用户id：{}", navName, status, userId);
        // 调用 service 层获取导航列表
        PageResponse<SpaceNavDTO> result = spaceNavService.getNavsByUserId(userId, spaceNavQueryDTO, pageIndex, pageSize);
        // 返回导航列表
        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), result);
    }

    @PostMapping
    @Operation(summary = "添加导航菜单", description = "根据提供的导航信息添加新的导航菜单")
    @ApiResponse(responseCode = "200", description = "成功添加导航菜单")
    public ResponseEntity<String> addNavMenu(
        @RequestBody @Valid SpaceNavDTO spaceNavDTO,
        @RequestAttribute("userId") Long userId
    ) {
        // 调用 service 层添加导航菜单
        String result = spaceNavService.addNavMenu(userId, spaceNavDTO);

        return ResponseEntity.custom(ResponseCodeEnum.SUCCESS.getCode(), result, null);
    }

}
