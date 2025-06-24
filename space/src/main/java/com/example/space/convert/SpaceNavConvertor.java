package com.example.space.convert;

import com.example.space.dto.SpaceNavDTO;
import com.example.space.enums.BooleanEnum;
import com.example.space.model.SpaceNav;
import com.example.space.vo.SpaceNavVO;

public class SpaceNavConvertor {
    // 单例
    public static final SpaceNavConvertor INSTANCE = new SpaceNavConvertor();

    // VO -> DTO
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

    // DTO -> Entity
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
        entity.setStatus(spaceNavDTO.getStatus() ? 0 : 1);

        return entity;
    }

    // VO -> Entity
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
        entity.setStatus(spaceNavVO.getStatus() ? 0 : 1);

        return entity;
    }

}

