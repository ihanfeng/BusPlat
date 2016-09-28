/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-09-28 18:00:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint(20) NOT NULL,
  `serialno` varchar(1000) DEFAULT NULL,
  `imei` varchar(1000) DEFAULT NULL,
  `uuid` varchar(1000) DEFAULT NULL COMMENT '设备唯一标识符',
  `model` varchar(1000) DEFAULT NULL,
  `idfa` varchar(1000) DEFAULT NULL COMMENT '广告位标识符idfa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for device_copy
-- ----------------------------
DROP TABLE IF EXISTS `device_copy`;
CREATE TABLE `device_copy` (
  `id` bigint(20) NOT NULL,
  `system_name` varchar(1000) DEFAULT NULL COMMENT '系统名称',
  `system_version` varchar(1000) DEFAULT NULL COMMENT '系统版本号',
  `ip_addr` varchar(1000) DEFAULT NULL,
  `mac_addr` varchar(255) DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL COMMENT 'App的名称',
  `app_version` varchar(1000) DEFAULT NULL COMMENT 'App的版本号',
  `app_build_version` varchar(1000) DEFAULT NULL COMMENT 'App build版本号',
  `loc_lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `loc_lon` varchar(1000) DEFAULT NULL COMMENT '经度',
  `loc_coord` varchar(100) DEFAULT NULL COMMENT '坐标系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device_copy
-- ----------------------------

-- ----------------------------
-- Table structure for zhiyin_community_comment_info
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_comment_info`;
CREATE TABLE `zhiyin_community_comment_info` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `thumb_num` int(11) DEFAULT '0',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  `target_user_id` bigint(20) DEFAULT NULL COMMENT '评论回复的用户',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '评论的目标id',
  `type` int(11) DEFAULT '1' COMMENT '1 回复 2 评论回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhiyin_community_comment_info
-- ----------------------------
INSERT INTO `zhiyin_community_comment_info` VALUES ('52042945921024', '52042922905600', '8207', 'tttt', '2', null, '2016-08-24 13:23:17', '2016-08-24 13:23:17', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52042948268032', '52042922905600', '7039', 'tttt', '2', null, '2016-08-24 13:23:18', '2016-08-24 13:23:18', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090727817216', '52090724515840', '6710', 'tttt', '0', null, '2016-08-24 16:37:43', '2016-08-24 16:37:43', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090729820160', '52090724515840', '1083', 'tttt', '0', null, '2016-08-24 16:37:43', '2016-08-24 16:37:43', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090730598400', '52090730332160', '2471', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090731286528', '52090730332160', '6296', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090732191744', '52090731925504', '2018', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090732781568', '52090731925504', '7230', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090733445120', '52090733096960', '7160', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090733899776', '52090733096960', '1414', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090734485504', '52090734321664', '930', 'tttt', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52090735079424', '52090734321664', '1644', 'tttt', '0', null, '2016-08-24 16:37:45', '2016-08-24 16:37:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093190455296', '52093189226496', '5180', 'tttt', '0', null, '2016-08-24 16:47:44', '2016-08-24 16:47:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093191520256', '52093189226496', '5491', 'tttt', '0', null, '2016-08-24 16:47:44', '2016-08-24 16:47:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093192118272', '52093191966720', '2323', 'tttt', '0', null, '2016-08-24 16:47:44', '2016-08-24 16:47:44', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093192802304', '52093191966720', '9742', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093193330688', '52093193170944', '5922', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093193797632', '52093193170944', '8339', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093194424320', '52093194199040', '5419', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093194817536', '52093194199040', '8353', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093195468800', '52093195268096', '7232', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52093196193792', '52093195268096', '2392', 'tttt', '0', null, '2016-08-24 16:47:45', '2016-08-24 16:47:45', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52099324227584', '52099303804928', '4643', 'tttt', '2', null, '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52099324604416', '52099303804928', '6571', 'tttt', '2', null, '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52099763752960', '52099742572544', '7061', 'tttt', '2', null, '2016-08-24 17:14:29', '2016-08-24 17:14:29', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52099764523008', '52099742572544', '5696', 'tttt', '2', null, '2016-08-24 17:14:29', '2016-08-24 17:14:29', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52101127258112', '52101107331072', '6224', 'tttt', '2', null, '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52101127585792', '52101107331072', '7782', 'tttt', '2', null, '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52101659422720', '52101639782400', '7959', 'tttt', '2', null, '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0', null, '0', '1');
INSERT INTO `zhiyin_community_comment_info` VALUES ('52101659881472', '52101639782400', '1777', 'tttt', '2', null, '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0', null, '0', '1');
