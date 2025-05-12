-- MySQL dump 10.13  Distrib 5.7.23, for Win64 (x86_64)
--
-- Host: localhost    Database: space
-- ------------------------------------------------------
-- Server version	5.7.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `space_coin_log`
--

DROP TABLE IF EXISTS `space_coin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_coin_log` (
                                  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                  `dailys_id` varchar(50) NOT NULL COMMENT '动态ID',
                                  `user1_id` varchar(32) NOT NULL COMMENT '被投币用户学号',
                                  `user2_id` varchar(32) NOT NULL COMMENT '投币用户学号',
                                  `coin_cnt` int(11) DEFAULT '0' COMMENT '历史投币数',
                                  `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                  `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                  `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='投币日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_coin_log`
--

LOCK TABLES `space_coin_log` WRITE;
/*!40000 ALTER TABLE `space_coin_log` DISABLE KEYS */;
INSERT INTO `space_coin_log` VALUES (4,'17240106@1620967758875','17240106','root',2,'2021-06-02 20:37:35','2021-06-02 12:37:35',0);
/*!40000 ALTER TABLE `space_coin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_comment`
--

DROP TABLE IF EXISTS `space_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_comment` (
                                 `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                 `dailys_id` varchar(50) NOT NULL COMMENT '动态ID',
                                 `comment1_id` varchar(32) NOT NULL COMMENT '被评论用户ID',
                                 `comment2_id` varchar(32) NOT NULL COMMENT '评论用户ID',
                                 `comment_content` varchar(1024) NOT NULL COMMENT '评论内容',
                                 `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                 `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                 `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COMMENT='动态评论表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_comment`
--

LOCK TABLES `space_comment` WRITE;
/*!40000 ALTER TABLE `space_comment` DISABLE KEYS */;
INSERT INTO `space_comment` VALUES (7,'17240113@1620541081229','17240113','root','太差了','2021-05-10 23:01:07','2021-05-11 05:01:30',0),(8,'17240113@1620541081229','17240113','root','太好了','2021-05-10 23:01:16','2021-05-11 05:01:32',0),(9,'17240113@1620541081229','17240113','root','一般','2021-05-10 23:16:06','2021-06-02 04:35:23',0),(10,'17240113@1620541081229','17240113','root','还不错','2021-05-10 23:17:15','2021-05-11 05:01:36',0),(11,'17240113@1620541081229','17240113','root','挺好的','2021-05-11 13:00:05','2021-05-11 05:00:05',0),(12,'root@1620541187034','root','root','还可以','2021-05-11 13:02:00','2021-05-11 05:02:00',0),(13,'root@1620541187034','root','root','我觉得还不错','2021-05-11 13:12:34','2021-06-02 04:35:30',0),(14,'root@1620541187034','root','root','一般','2021-05-11 13:13:17','2021-06-02 04:35:36',0),(15,'root@1620541187034','root','root','太好了','2021-05-14 03:20:16','2021-06-02 04:35:39',0),(17,'17240106@1620967758875','17240113','root','还可以','2021-05-23 18:16:20','2021-06-02 12:48:38',0),(18,'17240106@1620967758875','17240106','root','太好了','2021-06-02 20:51:08','2021-06-02 12:51:08',0);
/*!40000 ALTER TABLE `space_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_comment_anls`
--

DROP TABLE IF EXISTS `space_comment_anls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_comment_anls` (
                                      `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `praise_set` varchar(12) DEFAULT NULL COMMENT '好评关键词',
                                      `negative_set` varchar(12) DEFAULT NULL COMMENT '差评关键词',
                                      `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                      `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                      `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='评论分析设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_comment_anls`
--

LOCK TABLES `space_comment_anls` WRITE;
/*!40000 ALTER TABLE `space_comment_anls` DISABLE KEYS */;
INSERT INTO `space_comment_anls` VALUES (34,'太好了',NULL,'2021-05-27 15:31:26','2021-05-27 07:31:26',0),(35,'不错',NULL,'2021-05-27 15:31:26','2021-05-27 07:31:26',0),(36,NULL,'太差了','2021-05-27 15:31:34','2021-05-27 07:31:34',0),(37,NULL,'一般','2021-05-27 15:31:34','2021-05-27 07:31:34',0);
/*!40000 ALTER TABLE `space_comment_anls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_daily`
--

DROP TABLE IF EXISTS `space_daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_daily` (
                               `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                               `user_id` varchar(32) NOT NULL COMMENT '发布动态用户学号',
                               `daily_id` varchar(50) NOT NULL COMMENT '动态ID',
                               `daily_tag1` varchar(10) DEFAULT NULL COMMENT '动态标签1',
                               `daily_tag2` varchar(10) DEFAULT NULL COMMENT '动态标签2',
                               `daily_content` varchar(1024) NOT NULL COMMENT '动态内容',
                               `daily_img_list` varchar(1024) DEFAULT NULL COMMENT '动态图片列表',
                               `favor_cnt` int(11) DEFAULT '0' COMMENT '点赞数',
                               `coin_cnt` int(11) DEFAULT '0' COMMENT '投币数',
                               `praise_cnt` int(11) DEFAULT '0' COMMENT '好评数',
                               `negative_cnt` int(11) DEFAULT '0' COMMENT '差评数',
                               `avg_cnt` int(11) DEFAULT '0' COMMENT '普通评论数',
                               `effect_cnt` int(11) DEFAULT '0' COMMENT '热度/影响力',
                               `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                               `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                               `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='校园动态表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_daily`
--

LOCK TABLES `space_daily` WRITE;
/*!40000 ALTER TABLE `space_daily` DISABLE KEYS */;
INSERT INTO `space_daily` VALUES (1,'17240113','17240113@1620541081229',NULL,NULL,'在精神的废墟上聚拢起零碎的希望之光','1620548624028-01.jpg',100,22,33,0,0,1341,'2021-05-09 14:18:01','2021-06-02 13:58:12',0),(3,'root','root@1620548624039',NULL,NULL,'岁月一点一滴的溜走，在不经意间，快的让我们都来不及在下一个路口挽留。也无法预测人生未知的镜头。','1620548624028-01.jpg@1620548624029-02.jpg',0,0,0,0,0,0,'2021-05-09 16:23:44','2021-05-30 11:42:39',0),(4,'17240102','17240102@1620967052130',NULL,NULL,'愿你生命中有够多的云翳，来造成一个美丽的黄昏。','1620967052117-01.webp@1620967052118-02.webp',22,33,42,8,0,654,'2021-05-14 12:37:32','2021-06-02 13:58:12',0),(5,'17240103','17240103@1620967153116',NULL,NULL,'向这太阳努力冲，抓住心里的那一抹橙色','1620967153104-01.webp',10,20,12,4,0,300,'2021-05-14 12:39:13','2021-06-02 13:58:12',0),(6,'17240104','17240104@1620967341354','感悟',NULL,'在精神的废墟上聚拢起零碎的希望之光','1620967341345-01.webp',8,11,15,1,0,238,'2021-05-14 12:42:21','2021-06-02 13:58:12',0),(7,'17240105','17240105@1620967626661','心得',NULL,'愿所有停留不了的爱，洁白如兰花，纵使明日又隔天涯','1620967626648-01.webp',21,16,8,3,0,363,'2021-05-14 12:47:06','2021-06-02 13:58:12',0),(8,'17240106','17240106@1620967758875','心得',NULL,'有多少永远值得坚持，有多少永远配得上永远。','1620967758863-01.webp',15,7,14,7,0,241,'2021-05-14 12:49:18','2021-06-02 13:58:12',0),(10,'root','root@1622638975030',NULL,NULL,'当世界给草籽重压时，它总会用自己的方法破土而出。','1622638974996-01.webp@1622638974996-02.webp',0,0,0,0,0,0,'2021-06-02 21:02:55','2021-06-02 13:02:55',0),(11,'17240113','17240113@16205410819999',NULL,NULL,'我们可以失望，但不能盲目','1620967758863-01.webp',100,22,33,0,2,1347,'2021-05-09 14:18:01','2021-06-02 14:05:08',0);
/*!40000 ALTER TABLE `space_daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_effect_set`
--

DROP TABLE IF EXISTS `space_effect_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_effect_set` (
                                    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `effect_key` varchar(32) NOT NULL COMMENT '热度设置项',
                                    `effect_val` int(11) DEFAULT '0' COMMENT '热度设置值',
                                    `effect_note` varchar(50) NOT NULL COMMENT '备注',
                                    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                    `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='热度设置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_effect_set`
--

LOCK TABLES `space_effect_set` WRITE;
/*!40000 ALTER TABLE `space_effect_set` DISABLE KEYS */;
INSERT INTO `space_effect_set` VALUES (1,'favor_val',10,'点赞热度','2021-04-26 17:01:33','2021-06-02 13:58:12',0),(2,'coin_val',8,'投币热度','2021-04-26 17:01:33','2021-04-26 09:01:33',0),(3,'praise_val',5,'好评热度','2021-04-26 17:01:33','2021-04-26 09:01:33',0),(4,'negative_val',-5,'差评热度','2021-04-26 17:01:33','2021-05-12 13:07:29',0),(5,'avg_val',3,'普通评论热度','2021-04-26 17:01:33','2021-04-26 09:01:33',0);
/*!40000 ALTER TABLE `space_effect_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_favor_log`
--

DROP TABLE IF EXISTS `space_favor_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_favor_log` (
                                   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `dailys_id` varchar(50) NOT NULL COMMENT '动态ID',
                                   `user1_id` varchar(32) NOT NULL COMMENT '被点赞用户学号',
                                   `user2_id` varchar(32) NOT NULL COMMENT '点赞用户学号',
                                   `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                   `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='点赞日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_favor_log`
--

LOCK TABLES `space_favor_log` WRITE;
/*!40000 ALTER TABLE `space_favor_log` DISABLE KEYS */;
INSERT INTO `space_favor_log` VALUES (1,'17240106@1620967758875','17240106','root','2021-05-21 19:50:06','2021-06-02 12:25:44',0),(2,'17240105@1620967626661','17240105','root','2021-05-21 20:29:34','2021-05-31 11:05:07',-1038529930),(3,'17240102@1620967052130','17240102','root','2021-05-22 20:09:59','2021-05-22 12:10:06',-1812231567),(4,'root@1621765815148','root','root','2021-05-30 22:03:00','2021-06-01 12:38:49',-946508565),(5,'17240113@1620541081229','17240113','root','2021-06-01 20:04:21','2021-06-01 12:04:29',-948568690);
/*!40000 ALTER TABLE `space_favor_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_info_check`
--

DROP TABLE IF EXISTS `space_info_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_info_check` (
                                    `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `user_id` varchar(32) NOT NULL COMMENT '学号',
                                    `user_realname` varchar(24) DEFAULT NULL COMMENT '真实姓名',
                                    `user_idCard` varchar(24) DEFAULT NULL COMMENT '身份证号',
                                    `user_phone` varchar(24) DEFAULT NULL COMMENT '联系电话',
                                    `user_mail` varchar(24) DEFAULT NULL COMMENT '联系邮箱',
                                    `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                    `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                    `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COMMENT='信息核对表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_info_check`
--

LOCK TABLES `space_info_check` WRITE;
/*!40000 ALTER TABLE `space_info_check` DISABLE KEYS */;
INSERT INTO `space_info_check` VALUES (26,'17240101','朱梦雨','130106199803231819','13832175020','1158984036@qq.com','2021-05-23 18:45:30','2021-05-23 10:45:30',0),(27,'17240102','李佳','130106199803231819','13832175021','787168236@qq.com','2021-05-23 18:45:30','2021-05-23 10:45:30',0),(28,'17240103','李嘉雯','130106199803231819','13832175022','2358984569@qq.com','2021-05-23 18:45:30','2021-05-23 10:45:30',0),(29,'17240104','张翊文','130106199803231819','13832175023','1522898726@qq.com','2021-05-23 18:45:30','2021-05-23 10:45:30',0),(30,'17240105','张婧','130106199803231819','13832175024','151871031@qq.com','2021-05-23 18:45:30','2021-05-23 10:45:30',0);
/*!40000 ALTER TABLE `space_info_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_nav`
--

DROP TABLE IF EXISTS `space_nav`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_nav` (
                             `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                             `parent_id` int(11) unsigned DEFAULT NULL COMMENT '父级导航id',
                             `nav_name` varchar(32) DEFAULT NULL COMMENT '导航名称',
                             `nav_url` varchar(32) DEFAULT NULL COMMENT '导航跳转链接',
                             `nav_icon` varchar(255) DEFAULT NULL COMMENT '导航图标',
                             `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                             `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                             `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `fk_parent_id` (`parent_id`) USING BTREE,
                             CONSTRAINT `fk_parent_id` FOREIGN KEY (`parent_id`) REFERENCES `space_nav` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='系统导航';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_nav`
--

LOCK TABLES `space_nav` WRITE;
/*!40000 ALTER TABLE `space_nav` DISABLE KEYS */;
INSERT INTO `space_nav` VALUES (1,NULL,'系统管理',NULL,NULL,'2021-04-26 19:36:47','2021-04-26 11:36:53',0),(2,NULL,'用户管理',NULL,'nav-icon fas fa-users-cog','2021-04-26 19:37:51','2021-04-26 11:37:51',0),(3,2,'用户信息','user','far fa-circle nav-icon','2021-04-26 21:01:32','2021-04-26 13:02:49',0),(4,2,'角色管理','role','far fa-circle nav-icon','2021-04-26 21:02:34','2021-04-26 13:02:47',0),(5,2,'角色权限管理','roleRule','far fa-circle nav-icon','2021-04-26 21:02:43','2021-04-26 13:03:07',0),(6,NULL,'导航管理','nav','nav-icon fas fa-align-left','2021-04-26 21:03:51','2021-04-26 13:03:51',0),(7,NULL,'热度设置','effect','nav-icon fas fa-cogs','2021-04-26 21:04:48','2021-04-26 13:04:48',0),(8,NULL,'动态列表',NULL,NULL,'2021-04-26 21:05:26','2021-04-26 13:05:26',0),(9,NULL,'全部动态','allDaily','far fa-circle nav-icon','2021-04-26 21:05:45','2021-04-26 13:05:53',0),(10,NULL,'我的动态','myDaily','far fa-circle nav-icon','2021-04-26 21:06:10','2021-04-26 13:06:14',0);
/*!40000 ALTER TABLE `space_nav` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_role`
--

DROP TABLE IF EXISTS `space_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_role` (
                              `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `role_name` varchar(12) DEFAULT NULL COMMENT '角色名称',
                              `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                              PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='系统角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_role`
--

LOCK TABLES `space_role` WRITE;
/*!40000 ALTER TABLE `space_role` DISABLE KEYS */;
INSERT INTO `space_role` VALUES (1,'超级管理员','2021-04-12 14:53:06','2021-05-19 13:21:36',0),(2,'管理员','2021-04-12 14:53:19','2021-04-14 03:49:54',0),(3,'普通用户','2021-04-12 14:53:27','2021-04-14 06:53:27',0);
/*!40000 ALTER TABLE `space_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_role_rule`
--

DROP TABLE IF EXISTS `space_role_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_role_rule` (
                                   `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                   `role_id` int(11) unsigned NOT NULL COMMENT '关联的角色',
                                   `nav_id` int(11) unsigned NOT NULL COMMENT '关联的导航',
                                   `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                   `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                   `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `fk_role_id1` (`role_id`),
                                   KEY `fk_nav_id` (`nav_id`),
                                   CONSTRAINT `fk_nav_id` FOREIGN KEY (`nav_id`) REFERENCES `space_nav` (`id`) ON UPDATE NO ACTION,
                                   CONSTRAINT `fk_role_id1` FOREIGN KEY (`role_id`) REFERENCES `space_role` (`id`) ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_role_rule`
--

LOCK TABLES `space_role_rule` WRITE;
/*!40000 ALTER TABLE `space_role_rule` DISABLE KEYS */;
/*!40000 ALTER TABLE `space_role_rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_user`
--

DROP TABLE IF EXISTS `space_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_user` (
                              `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                              `role_id` int(11) unsigned DEFAULT '3' COMMENT '角色ID',
                              `user_id` varchar(32) NOT NULL COMMENT '登录学号',
                              `enjoy_user` varchar(1024) DEFAULT NULL COMMENT '关注用户',
                              `enjoy_tag` varchar(2048) DEFAULT NULL COMMENT '感兴趣标签',
                              `user_passwd` char(32) DEFAULT '123456' COMMENT '登录密码',
                              `user_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
                              `user_realname` varchar(24) DEFAULT NULL COMMENT '真实姓名',
                              `user_idCard` varchar(24) DEFAULT NULL COMMENT '身份证号',
                              `user_phone` varchar(24) DEFAULT NULL COMMENT '联系电话',
                              `user_mail` varchar(24) DEFAULT NULL COMMENT '联系邮箱',
                              `favor_cnt` int(11) DEFAULT '0' COMMENT '点赞数',
                              `coin_cnt` int(11) DEFAULT '0' COMMENT '投币数',
                              `coin_num` int(11) DEFAULT '5' COMMENT '硬币数',
                              `praise_cnt` int(11) DEFAULT '0' COMMENT '好评数',
                              `negative_cnt` int(11) DEFAULT '0' COMMENT '差评数',
                              `avg_cnt` int(11) DEFAULT '0' COMMENT '普通评论数',
                              `effect_cnt` int(11) DEFAULT '0' COMMENT '热度（影响力）',
                              `personal_honor` varchar(1024) DEFAULT NULL COMMENT '个人荣誉信息',
                              `user_last_time` datetime DEFAULT NULL COMMENT '上一次登录的时间',
                              `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                              `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                              `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                              PRIMARY KEY (`id`) USING BTREE,
                              KEY `fk_user_role_id` (`role_id`) USING BTREE,
                              CONSTRAINT `fk_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `space_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_user`
--

LOCK TABLES `space_user` WRITE;
/*!40000 ALTER TABLE `space_user` DISABLE KEYS */;
INSERT INTO `space_user` VALUES (1,1,'root','17240113','心得','123456','1622549956819.jpg','root','无','无','无',0,0,-5,0,0,0,0,'root超级用户',NULL,'2021-04-26 17:01:32','2021-06-02 12:37:41',0),(2,3,'17240113',NULL,NULL,'123456','1622105194510.jpg','孙广超','130106199803231819','13832175020','1158984036@qq.com',3,2,5,3,1,0,56,'17240113孙广超，在大学期间获得2018-2019、2019-2020学年三好学生、2018-2019学年一等奖学金、2019-2020学年二等奖学金。专业竞赛方面获第十届、第十一届蓝桥杯大赛江苏省一等奖，第十一届蓝桥杯大赛全国总决赛三等奖',NULL,'2021-05-09 08:19:42','2021-06-02 13:58:12',0),(3,3,'17240101',NULL,NULL,'123456','1622105194510.jpg','朱梦雨','','','',6,2,2,7,0,0,111,'',NULL,'2021-05-14 11:35:07','2021-06-02 13:58:12',0),(4,3,'17240102',NULL,NULL,'123456','1622105332708.jpg','李佳','','','',4,4,5,2,0,2,88,'',NULL,'2021-05-14 11:35:23','2021-06-02 13:58:12',0),(5,3,'17240103',NULL,NULL,'123456','1622105194510.jpg','李嘉雯','','','',8,2,5,8,2,3,135,'',NULL,'2021-05-14 11:35:33','2021-06-02 13:58:12',0),(6,3,'17240104',NULL,NULL,'123456','1622105332708.jpg','张翊文','','','',7,3,5,6,4,4,116,'',NULL,'2021-05-14 11:35:50','2021-06-02 13:58:12',0),(7,3,'17240105',NULL,NULL,'123456','1622105194510.jpg','张婧','','','',4,6,5,2,2,3,97,'',NULL,'2021-05-14 11:36:05','2021-06-02 13:58:12',0),(8,3,'17240106',NULL,NULL,'123456','1622105091919.jpg','贾梦婷','','','',4,11,5,7,3,8,172,'',NULL,'2021-05-14 11:37:05','2021-06-02 13:58:12',0),(9,3,'17240107',NULL,NULL,'123456','1622105150746.jpg','凌新云','','','',0,0,5,0,0,0,0,'',NULL,'2021-05-14 11:37:15','2021-06-01 12:03:03',0),(10,3,'17240108',NULL,NULL,'123456','1622105332708.jpg','程莲','','','',0,0,5,0,0,0,0,'',NULL,'2021-05-14 11:37:23','2021-06-01 12:03:17',0),(11,3,'17240109',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-14 11:37:35','2021-05-14 03:37:35',0),(12,3,'17210202',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-20 14:44:30','2021-05-20 06:44:30',0),(13,3,'18410106',NULL,NULL,'123456','1622105091919.jpg','单宇',NULL,NULL,NULL,12,30,5,11,2,0,405,'单宇，曾获一等奖学金、三好学生、优秀共青团员、社团优秀干部，2019年全国大学生英语竞赛C类二等奖、第十一届全国大学生数学竞赛三等奖、江苏省高等学校第十六届高数竞赛本科二级组二等奖、2020年全国大学生数学建模竞赛省级三等奖、2020年数维杯大学生数学建模竞赛本科组三等奖、第十二届南京邮电大学数学建模竞赛二等奖等荣誉奖项',NULL,'2021-05-20 14:49:46','2021-06-02 13:58:12',0),(14,3,'17210201',NULL,NULL,'123456','1622105150746.jpg','孙宇飞',NULL,NULL,NULL,15,18,5,22,5,0,379,'孙宇飞，曾获一等奖学金、三好学生、优秀共青团团员、社团积极分子、江苏省普通高等学校第十五届高等数学竞赛本科三级组二等奖、2019年江苏省大学生程学设计大赛三等奖、第十一届蓝桥杯全国软件和信息技术专业人才大赛江苏赛区C/C++程序设计大学B组一等奖等荣誉奖项',NULL,'2021-05-23 16:54:20','2021-06-02 13:58:12',0),(15,3,'17240201',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-23 17:03:37','2021-05-23 09:03:37',0),(16,3,'17540303',NULL,NULL,'123456','1622105194510.jpg','沈成',NULL,NULL,NULL,22,7,5,16,4,0,336,'沈成，中共党员，现任班级班长。曾获优秀学生干部、优秀社团干部、第十届世界华语锦标赛江苏赛区初赛最佳辩手、2019年学院暑假三下乡先进团队评选活动一等奖、第四届让我们的心理更阳光演讲比赛二等奖、第四届校园思辨赛四人制冠军等荣誉奖项',NULL,'2021-05-27 16:07:30','2021-06-02 13:58:12',0),(17,3,'17410104',NULL,NULL,'123456','1622105254642.jpg','袁必康',NULL,NULL,NULL,18,11,5,5,2,0,283,'袁必康，中共党员，现任班级班长。曾获三好学生标兵、2018年江苏省大学生电子设计大赛一等奖、2019全国大学生电子设计大赛江苏赛区二等奖、2019年第十六届五一数学建模竞赛三等奖等荣誉奖项，完成2019年大学生科技创新训练计划（STITP）省级项目、校级项目各一项，并发表论文两篇',NULL,'2021-05-27 16:07:52','2021-06-02 13:58:12',0),(18,3,'18570303',NULL,NULL,'123456','1622105332708.jpg','栾小璇',NULL,NULL,NULL,16,20,5,7,3,0,340,'栾小璇，现任专业大班长。曾获一等奖学金、三好学生标兵、优秀团干部、十佳团支书、第一节江苏省精创教育杯大学生人力资源管理技能挑战赛二等奖、正大杯第十届全国大学生市场调查与分析大赛总决赛三等奖、第六届LSCAT杯江苏省笔译大赛（汉译英本科组）三等奖等荣誉奖项',NULL,'2021-05-27 16:13:44','2021-06-02 13:58:12',0),(19,3,'123456',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-28 20:43:30','2021-05-28 12:43:30',0),(20,3,'147852',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-28 20:45:00','2021-05-28 12:45:00',0),(21,3,'123',NULL,NULL,'123456',NULL,NULL,NULL,NULL,NULL,0,0,5,0,0,0,0,NULL,NULL,'2021-05-28 20:59:05','2021-05-28 12:59:05',0);
/*!40000 ALTER TABLE `space_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `space_user_recycle`
--

DROP TABLE IF EXISTS `space_user_recycle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `space_user_recycle` (
                                      `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `role_id` int(11) unsigned DEFAULT NULL COMMENT '角色ID',
                                      `user_id` varchar(32) NOT NULL COMMENT '登录学号',
                                      `user_passwd` char(32) DEFAULT NULL COMMENT '登录密码',
                                      `user_icon` varchar(255) DEFAULT NULL COMMENT '用户头像',
                                      `user_realname` varchar(24) DEFAULT NULL COMMENT '真实姓名',
                                      `user_idCard` varchar(24) DEFAULT NULL COMMENT '身份证号',
                                      `user_phone` varchar(24) DEFAULT NULL COMMENT '联系电话',
                                      `user_mail` varchar(24) DEFAULT NULL COMMENT '联系邮箱',
                                      `favor_cnt` int(11) DEFAULT '0' COMMENT '点赞数',
                                      `coin_cnt` int(11) DEFAULT '0' COMMENT '投币数',
                                      `coin_num` int(11) DEFAULT '5' COMMENT '硬币数',
                                      `praise_cnt` int(11) DEFAULT '0' COMMENT '好评数',
                                      `negative_cnt` int(11) DEFAULT '0' COMMENT '差评数',
                                      `avg_cnt` int(11) DEFAULT '0' COMMENT '普通评论数',
                                      `effect_cnt` int(11) DEFAULT '0' COMMENT '热度（影响力）',
                                      `personal_honor` varchar(1024) DEFAULT NULL COMMENT '个人荣誉信息',
                                      `user_last_time` datetime DEFAULT NULL COMMENT '上一次登录的时间',
                                      `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数据添加时间',
                                      `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '数据修改时间',
                                      `deltime` int(10) NOT NULL DEFAULT '0' COMMENT '数据删除时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='回收用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `space_user_recycle`
--

LOCK TABLES `space_user_recycle` WRITE;
/*!40000 ALTER TABLE `space_user_recycle` DISABLE KEYS */;
/*!40000 ALTER TABLE `space_user_recycle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) DEFAULT NULL,
                        `auth_code` bigint(20) DEFAULT NULL,
                        `password` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-12 19:15:39
