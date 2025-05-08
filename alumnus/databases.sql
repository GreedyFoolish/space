CREATE DATABASE IF NOT EXISTS `almn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
--ALTER TABLE almn_daily auto_increment = select max(id) from almn_daily;重置id
DROP TABLE IF EXISTS `almn_role`;
CREATE TABLE `almn_role`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE=InnoDB COMMENT='系统角色表';

DROP TABLE IF EXISTS `almn_user`;
CREATE TABLE `almn_user`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_id` int(11) UNSIGNED DEFAULT NULL COMMENT '角色ID',
	`user_id` varchar(255) NOT NULL COMMENT '登录学号',
	`user_passwd` char(32) DEFAULT NULL COMMENT '登录密码',
	`user_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
	`user_realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
	`user_idCard` varchar(255) DEFAULT NULL COMMENT '身份证号',
	`user_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
	`user_mail` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
	`favor_cnt` int(11) DEFAULT 0 COMMENT '点赞数',
	`coin_cnt` int(11) DEFAULT 0 COMMENT '投币数',
	`coin_num` int(11) DEFAULT 5 COMMENT '硬币数',
	`praise_cnt` int(11) DEFAULT 0 COMMENT '好评数',
	`negative_cnt` int(11) DEFAULT 0 COMMENT '差评数',
	`avg_cnt` int(11) DEFAULT 0 COMMENT '普通评论数',
	`effect_cnt` int(11) DEFAULT 0 COMMENT '热度（影响力）',
	`personal_honor` varchar(1024) DEFAULT NULL COMMENT '个人荣誉信息',
	`user_last_time` datetime DEFAULT NULL COMMENT '上一次登录的时间',
	`addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY(`id`) USING BTREE,
	INDEX `fk_user_role_id`(`role_id`) USING BTREE,
	CONSTRAINT `fk_user_role_id` FOREIGN KEY(`role_id`) REFERENCES `almn_role`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
)ENGINE=InnoDB COMMENT='用户表';

DROP TABLE IF EXISTS `almn_nav`;
CREATE TABLE `almn_nav`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`parent_id` int(11) UNSIGNED DEFAULT NULL COMMENT '父级导航id',
	`nav_sort` int(11) UNSIGNED NOT NULL DEFAULT 50 COMMENT '导航链接的排序，数据越小排序越靠前',
	`nav_name` varchar(255) DEFAULT NULL COMMENT '导航名称',
	`nav_url` varchar(255) DEFAULT NULL COMMENT '导航跳转链接',
	`nav_icon` varchar(255) DEFAULT NULL COMMENT '导航的前置图标',
	`nav_badge` varchar(255) DEFAULT NULL COMMENT '导航的后置徽章',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY(`id`) USING BTREE,
	INDEX `fk_parent_id`(`parent_id`) USING BTREE,
	CONSTRAINT `fk_parent_id` FOREIGN KEY(`parent_id`) REFERENCES `almn_nav`(`id`) ON DELETE RESTRICT ON UPDATE NO ACTION
)ENGINE=InnoDB COMMENT='系统导航';

DROP TABLE IF EXISTS `almn_role_rule`;
CREATE TABLE `almn_role_rule` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_id` int(11) unsigned NOT NULL COMMENT '关联的角色',
    `nav_id` int(11) unsigned NOT NULL COMMENT '关联的导航',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `fk_role_id1` (`role_id`),
    KEY `fk_nav_id` (`nav_id`),
    CONSTRAINT `fk_nav_id` FOREIGN KEY (`nav_id`) REFERENCES `almn_nav` (`id`) ON UPDATE NO ACTION,
    CONSTRAINT `fk_role_id1` FOREIGN KEY (`role_id`) REFERENCES `almn_role` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

DROP TABLE IF EXISTS `almn_daily`;
CREATE TABLE `almn_daily`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` varchar(255) NOT NULL COMMENT '发布动态用户学号',
	`dailys_id` varchar(255) NOT NULL COMMENT '动态ID',
	`dailys_content` varchar(2048) NOT NULL COMMENT '动态内容',
	`favor_cnt` int(11) DEFAULT 0 COMMENT '点赞数',
	`coin_cnt` int(11) DEFAULT 0 COMMENT '投币数',
	`praise_cnt` int(11) DEFAULT 0 COMMENT '好评数',
	`negative_cnt` int(11) DEFAULT 0 COMMENT '差评数',
	`avg_cnt` int(11) DEFAULT 0 COMMENT '普通评论数',
	`effect_cnt` int(11) DEFAULT 0 COMMENT '热度/影响力',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='校园动态表';

DROP TABLE IF EXISTS `almn_comment`;
CREATE TABLE `almn_comment`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` varchar(255) NOT NULL COMMENT '发布动态用户学号',
	`dailys_id` varchar(255) NOT NULL COMMENT '动态ID',
	`comment_userId` varchar(255) NOT NULL COMMENT '评论用户学号',
	`comment_content` varchar(1024) NOT NULL COMMENT '评论内容',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='动态评论表';

DROP TABLE IF EXISTS `almn_info_check`;
CREATE TABLE `almn_info_check`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` varchar(255) NOT NULL COMMENT '学号',
	`user_realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
	`user_idCard` varchar(255) DEFAULT NULL COMMENT '身份证号',
	`user_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
	`user_mail` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='信息核对表';

DROP TABLE IF EXISTS `almn_favor_log`;
CREATE TABLE `almn_favor_log`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`dailys_id` varchar(255) NOT NULL COMMENT '动态ID',
	`user1_id` varchar(255) NOT NULL COMMENT '被点赞用户学号',
	`user2_id` varchar(255) NOT NULL COMMENT '点赞用户学号',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='点赞日志表';

DROP TABLE IF EXISTS `almn_coin_log`;
CREATE TABLE `almn_coin_log`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`dailys_id` varchar(255) NOT NULL COMMENT '动态ID',
	`user1_id` varchar(255) NOT NULL COMMENT '被投币用户学号',
	`user2_id` varchar(255) NOT NULL COMMENT '投币用户学号',
	`coin_cnt` int(11) DEFAULT 0 COMMENT '历史投币数',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='投币日志表';

DROP TABLE IF EXISTS `almn_effect_set`;
CREATE TABLE `almn_effect_set`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`effect_key` varchar(255) NOT NULL COMMENT '热度设置项',
	`effect_val` int(11) DEFAULT 0 COMMENT '热度设置值',
	`effect_note` varchar(255) NOT NULL COMMENT '备注',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='热度设置表';

--DROP TABLE IF EXISTS `almn_effect_set`;
--CREATE TABLE `almn_effect_set`(
--	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
--	`favor_val` int(11) DEFAULT 0 COMMENT '点赞热度',
--	`coin_val` int(11) DEFAULT 0 COMMENT '投币热度',
--	`praise_val` int(11) DEFAULT 0 COMMENT '好评热度',
--	`negative_val` int(11) DEFAULT 0 COMMENT '差评热度',
--	`avg_val` int(11) DEFAULT 0 COMMENT '普通评论热度',
--  `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
--  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
--	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
--	PRIMARY KEY (`id`)
--)ENGINE=InnoDB COMMENT='热度设置表';

DROP TABLE IF EXISTS `almn_comment_anls`;
CREATE TABLE `almn_comment_anls`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`praise_set` int(11) DEFAULT 0 COMMENT '好评关键词',
	`negative_set` int(11) DEFAULT 0 COMMENT '差评关键词',
    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='评论分析设置表';

DROP TABLE IF EXISTS `almn_user_recycle`;
CREATE TABLE `almn_user_recycle`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_id` int(11) UNSIGNED DEFAULT NULL COMMENT '角色ID',
	`user_id` varchar(255) NOT NULL COMMENT '登录学号',
	`user_passwd` char(32) DEFAULT NULL COMMENT '登录密码',
	`user_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
	`user_realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
	`user_idCard` varchar(255) DEFAULT NULL COMMENT '身份证号',
	`user_phone` varchar(255) DEFAULT NULL COMMENT '联系电话',
	`user_mail` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
	`favor_cnt` int(11) DEFAULT 0 COMMENT '点赞数',
	`coin_cnt` int(11) DEFAULT 0 COMMENT '投币数',
	`coin_num` int(11) DEFAULT 5 COMMENT '硬币数',
	`praise_cnt` int(11) DEFAULT 0 COMMENT '好评数',
	`negative_cnt` int(11) DEFAULT 0 COMMENT '差评数',
	`avg_cnt` int(11) DEFAULT 0 COMMENT '普通评论数',
	`effect_cnt` int(11) DEFAULT 0 COMMENT '热度（影响力）',
	`personal_honor` varchar(1024) DEFAULT NULL COMMENT '个人荣誉信息',
	`user_last_time` datetime DEFAULT NULL COMMENT '上一次登录的时间',
	`addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='用户回收站';


DROP TABLE IF EXISTS `almn_daily_img`;
CREATE TABLE `almn_daily_img`(
	`id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
	`daily_id` int(11) UNSIGNED DEFAULT NULL COMMENT '动态ID',
	`title` varchar(255) DEFAULT NULL COMMENT '标题',
	`img_fileName` varchar(255) DEFAULT NULL COMMENT '图片名称',
	`link` varchar(255) DEFAULT NULL COMMENT '链接',
	`weight` int(11) DEFAULT NULL COMMENT '文件大小',
	`addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
	`updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
	`deltime` int(10) NOT NULL DEFAULT 0 COMMENT '数据删除时间',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB COMMENT='动态图片表';

insert into `almn_user` (`user_id`,`user_passwd`,`addtime`) values ('root','123456',now());

insert into `almn_effect_set` (`effect_key`,`effect_val`,`effect_note`) values 
('favor_val',10,'点赞热度'),
('coin_val',8,'投币热度'),
('praise_val',5,'好评热度'),
('negative_val',5,'差评热度'),
('avg_val',3,'普通评论热度');



	





