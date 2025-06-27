package com.example.space.convert;

import com.example.space.dto.SpaceNavDTO;
import com.example.space.enums.BooleanEnum;
import com.example.space.enums.StatusEnum;
import com.example.space.model.SpaceNav;
import com.example.space.vo.SpaceNavVO;

public class SpaceNavConvertor {
    // 单例
    public static final SpaceNavConvertor INSTANCE = new SpaceNavConvertor();

    // VO 转换方法 (VO -> DTO, VO -> Entity)
    public SpaceNavDTO convertVoToDto(SpaceNavVO spaceNavVO) {
        if (spaceNavVO == null) {
            return null;
        }

        SpaceNavDTO dto = new SpaceNavDTO();
        dto.setId(spaceNavVO.getId());
        dto.setParentId(spaceNavVO.getParentId());
        dto.setNavType(spaceNavVO.getNavType());
        dto.setNavName(spaceNavVO.getNavName());
        dto.setNavRouteName(spaceNavVO.getNavRouteName());
        dto.setIsFrame(spaceNavVO.getIsFrame());
        dto.setNavUrl(spaceNavVO.getNavUrl());
        dto.setNavIcon(spaceNavVO.getNavIcon());
        dto.setIsCache(spaceNavVO.getIsCache());
        dto.setNavComponent(spaceNavVO.getNavComponent());
        dto.setIsVisible(spaceNavVO.getIsVisible());
        dto.setNavSort(spaceNavVO.getNavSort());
        dto.setStatus(spaceNavVO.getStatus());

        return dto;
    }

    public SpaceNav convertVoToEntity(SpaceNavVO spaceNavVO) {
        if (spaceNavVO == null) {
            return null;
        }

        SpaceNav entity = new SpaceNav();
        entity.setId(spaceNavVO.getId());
        entity.setParentId(spaceNavVO.getParentId());
        entity.setNavType(spaceNavVO.getNavType());
        entity.setNavName(spaceNavVO.getNavName());
        entity.setNavRouteName(spaceNavVO.getNavRouteName());
        entity.setIsFrame(BooleanEnum.toInteger(spaceNavVO.getIsFrame()));
        entity.setNavUrl(spaceNavVO.getNavUrl());
        entity.setNavIcon(spaceNavVO.getNavIcon());
        entity.setIsCache(BooleanEnum.toInteger(spaceNavVO.getIsCache()));
        entity.setNavComponent(spaceNavVO.getNavComponent());
        entity.setIsVisible(BooleanEnum.toInteger(spaceNavVO.getIsVisible()));
        entity.setNavSort(spaceNavVO.getNavSort());
        entity.setStatus(StatusEnum.toInteger(spaceNavVO.getStatus()));

        return entity;
    }

    // DTO 转换方法 (DTO -> VO, DTO -> Entity)
    public SpaceNavVO convertDTOToVO(SpaceNavDTO spaceNavDTO) {
        if (spaceNavDTO == null) {
            return null;
        }

        SpaceNavVO spaceNavVO = new SpaceNavVO();
        spaceNavVO.setId(spaceNavDTO.getId());
        spaceNavVO.setParentId(spaceNavDTO.getParentId());
        spaceNavVO.setNavType(spaceNavDTO.getNavType());
        spaceNavVO.setNavName(spaceNavDTO.getNavName());
        spaceNavVO.setNavRouteName(spaceNavDTO.getNavRouteName());
        spaceNavVO.setIsFrame(spaceNavDTO.getIsFrame());
        spaceNavVO.setNavUrl(spaceNavDTO.getNavUrl());
        spaceNavVO.setNavIcon(spaceNavDTO.getNavIcon());
        spaceNavVO.setIsCache(spaceNavDTO.getIsCache());
        spaceNavVO.setNavComponent(spaceNavDTO.getNavComponent());
        spaceNavVO.setIsVisible(spaceNavDTO.getIsVisible());
        spaceNavVO.setNavSort(spaceNavDTO.getNavSort());
        spaceNavVO.setStatus(spaceNavDTO.getStatus());

        return spaceNavVO;
    }

    public SpaceNav convertDtoToEntity(SpaceNavDTO spaceNavDTO) {
        if (spaceNavDTO == null) {
            return null;
        }

        SpaceNav entity = new SpaceNav();
        entity.setId(spaceNavDTO.getId());
        entity.setParentId(spaceNavDTO.getParentId());
        entity.setNavType(spaceNavDTO.getNavType());
        entity.setNavName(spaceNavDTO.getNavName());
        entity.setNavRouteName(spaceNavDTO.getNavRouteName());
        entity.setIsFrame(BooleanEnum.toInteger(spaceNavDTO.getIsFrame()));
        entity.setNavUrl(spaceNavDTO.getNavUrl());
        entity.setNavIcon(spaceNavDTO.getNavIcon());
        entity.setIsCache(BooleanEnum.toInteger(spaceNavDTO.getIsCache()));
        entity.setNavComponent(spaceNavDTO.getNavComponent());
        entity.setIsVisible(BooleanEnum.toInteger(spaceNavDTO.getIsVisible()));
        entity.setNavSort(spaceNavDTO.getNavSort());
        entity.setStatus(StatusEnum.toInteger(spaceNavDTO.getStatus()));

        return entity;
    }

    // Entity 转换方法 (Entity -> VO, Entity -> DTO)
    public SpaceNavVO convertEntityToVO(SpaceNav entity) {
        if (entity == null) {
            return null;
        }

        SpaceNavVO spaceNavVO = new SpaceNavVO();
        spaceNavVO.setId(entity.getId());
        spaceNavVO.setParentId(entity.getParentId());
        spaceNavVO.setNavType(entity.getNavType());
        spaceNavVO.setNavName(entity.getNavName());
        spaceNavVO.setNavRouteName(entity.getNavRouteName());
        spaceNavVO.setIsFrame(BooleanEnum.toBoolean(entity.getIsFrame()));
        spaceNavVO.setNavUrl(entity.getNavUrl());
        spaceNavVO.setNavIcon(entity.getNavIcon());
        spaceNavVO.setIsCache(BooleanEnum.toBoolean(entity.getIsCache()));
        spaceNavVO.setNavComponent(entity.getNavComponent());
        spaceNavVO.setIsVisible(BooleanEnum.toBoolean(entity.getIsVisible()));
        spaceNavVO.setNavSort(entity.getNavSort());
        spaceNavVO.setStatus(StatusEnum.toBoolean(entity.getStatus()));

        return spaceNavVO;
    }

    public SpaceNavDTO convertEntityToDTO(SpaceNav entity) {
        if (entity == null) {
            return null;
        }

        SpaceNavDTO dto = new SpaceNavDTO();
        dto.setId(entity.getId());
        dto.setParentId(entity.getParentId());
        dto.setNavType(entity.getNavType());
        dto.setNavName(entity.getNavName());
        dto.setNavRouteName(entity.getNavRouteName());
        dto.setIsFrame(BooleanEnum.toBoolean(entity.getIsFrame()));
        dto.setNavUrl(entity.getNavUrl());
        dto.setNavIcon(entity.getNavIcon());
        dto.setIsCache(BooleanEnum.toBoolean(entity.getIsCache()));
        dto.setNavComponent(entity.getNavComponent());
        dto.setIsVisible(BooleanEnum.toBoolean(entity.getIsVisible()));
        dto.setNavSort(entity.getNavSort());
        dto.setStatus(StatusEnum.toBoolean(entity.getStatus()));

        return dto;
    }

}

