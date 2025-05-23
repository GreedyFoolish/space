DROP TABLE IF EXISTS `space_role_rule`;
DROP TABLE IF EXISTS `space_user`;
DROP TABLE IF EXISTS `space_nav`;
DROP TABLE IF EXISTS `space_role`;

CREATE TABLE `space_role`
(
    `id`          BIGINT UNSIGNED                       NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `role_name`   VARCHAR(64) DEFAULT NULL COMMENT '角色名称',
    `auth_code`   INT         DEFAULT 1000 COMMENT '权限编码',
    `create_by`   BIGINT UNSIGNED                       NULL COMMENT '创建者',
    `update_by`   BIGINT UNSIGNED                       NULL COMMENT '更新者',
    `remark`      VARCHAR(255)                          NULL COMMENT '备注',
    `status`      INT         DEFAULT 0                 NOT NULL COMMENT '状态标志，0-正常，1-删除。默认值为0',
    `create_time` DATETIME    DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    `update_time` DATETIME    DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` DATETIME                              NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT ='系统角色表';

CREATE TABLE `space_user`
(
    `id`             BIGINT UNSIGNED AUTO_INCREMENT COMMENT '用户id',
    `role_id`        BIGINT UNSIGNED                    NULL COMMENT '角色id',
    `user_name`      VARCHAR(64)                        NULL COMMENT '用户名',
    `user_passwd`    VARCHAR(255)                       NULL COMMENT '用户密码',
    `user_last_time` DATETIME                           NULL COMMENT '上一次登录时间',
    `create_by`      BIGINT UNSIGNED                    NULL COMMENT '创建者',
    `update_by`      BIGINT UNSIGNED                    NULL COMMENT '更新者',
    `remark`         VARCHAR(255)                       NULL COMMENT '备注',
    `status`         INT      DEFAULT 0                 NOT NULL COMMENT '状态标志，0-正常，1-删除。默认值为0',
    `create_time`    DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    `update_time`    DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time`    DATETIME                           NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `spaceUser_spaceRole_id_fk` (`role_id`) USING BTREE,
    CONSTRAINT `spaceUser_spaceRole_id_fk` FOREIGN KEY (`role_id`) REFERENCES `space_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB COMMENT ='用户表';

CREATE TABLE `space_nav`
(
    `id`          BIGINT UNSIGNED AUTO_INCREMENT COMMENT '导航id',
    `parent_id`   BIGINT UNSIGNED                    NULL COMMENT '父级导航id',
    `nav_name`    VARCHAR(64)                        NULL COMMENT '导航名称',
    `nav_url`     VARCHAR(255)                       NULL COMMENT '导航路由',
    `nav_icon`    VARCHAR(255)                       NULL COMMENT '导航图标',
    `nav_sort`    INT UNSIGNED                       NOT NULL DEFAULT 50 COMMENT '导航排序，数值越小越靠前。默认值为50',
    `create_by`   BIGINT UNSIGNED                    NULL COMMENT '创建者',
    `update_by`   BIGINT UNSIGNED                    NULL COMMENT '更新者',
    `remark`      VARCHAR(255)                       NULL COMMENT '备注',
    `status`      INT      DEFAULT 0                 NOT NULL COMMENT '状态标志，0-正常，1-删除。默认值为0',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` DATETIME                           NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `spaceNav_spaceNav_id_fk` (`parent_id`) USING BTREE,
    CONSTRAINT `spaceNav_spaceNav_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `space_nav` (`id`) ON DELETE RESTRICT ON UPDATE NO ACTION
) ENGINE = InnoDB COMMENT ='导航菜单';

CREATE TABLE `space_role_rule`
(
    `id`          BIGINT UNSIGNED                    NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_id`     BIGINT UNSIGNED                    NOT NULL COMMENT '角色id',
    `nav_id`      BIGINT UNSIGNED                    NOT NULL COMMENT '导航id',
    `create_by`   BIGINT UNSIGNED                    NULL COMMENT '创建者',
    `update_by`   BIGINT UNSIGNED                    NULL COMMENT '更新者',
    `remark`      VARCHAR(255)                       NULL COMMENT '备注',
    `status`      INT      DEFAULT 0                 NOT NULL COMMENT '状态标志，0-正常，1-删除。默认值为0',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` DATETIME                           NULL COMMENT '删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `spaceRoleRule_spaceRole_id_fk` (`role_id`),
    KEY `spaceRoleRule_spaceNav_id_fk` (`nav_id`),
    CONSTRAINT `spaceRoleRule_spaceRole_id_fk` FOREIGN KEY (`role_id`) REFERENCES `space_role` (`id`) ON UPDATE NO ACTION,
    CONSTRAINT `spaceRoleRule_spaceNav_id_fk` FOREIGN KEY (`nav_id`) REFERENCES `space_nav` (`id`) ON UPDATE NO ACTION
) ENGINE = InnoDB COMMENT ='角色权限表';
