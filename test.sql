/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-09-28 17:59:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for binloguser
-- ----------------------------
DROP TABLE IF EXISTS `binloguser`;
CREATE TABLE `binloguser` (
  `id` int(11) NOT NULL,
  `firstname` char(40) DEFAULT NULL,
  `middlename` varchar(40) DEFAULT NULL,
  `lastname` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of binloguser
-- ----------------------------
INSERT INTO `binloguser` VALUES ('1', 'hg_222d', 'm3', 'tangss');
INSERT INTO `binloguser` VALUES ('3', 'f3', 'm3', 'wang');

-- ----------------------------
-- Table structure for cache_test_user_info
-- ----------------------------
DROP TABLE IF EXISTS `cache_test_user_info`;
CREATE TABLE `cache_test_user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cache_test_user_info
-- ----------------------------
INSERT INTO `cache_test_user_info` VALUES ('2', '20', 'BBB');
INSERT INTO `cache_test_user_info` VALUES ('3', '30', 'hhhhh');
INSERT INTO `cache_test_user_info` VALUES ('4', '40', 'DDD');
INSERT INTO `cache_test_user_info` VALUES ('5', '50', 'EEE');
INSERT INTO `cache_test_user_info` VALUES ('6', '60', 'FFF');
INSERT INTO `cache_test_user_info` VALUES ('7', '70', 'GGG');
INSERT INTO `cache_test_user_info` VALUES ('8', '80', 'HHH');
INSERT INTO `cache_test_user_info` VALUES ('9', '90', 'III');
INSERT INTO `cache_test_user_info` VALUES ('10', '100', 'JJJ');
INSERT INTO `cache_test_user_info` VALUES ('11', '10', 'hg');

-- ----------------------------
-- Table structure for catptcha
-- ----------------------------
DROP TABLE IF EXISTS `catptcha`;
CREATE TABLE `catptcha` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `pic` varchar(255) DEFAULT '' COMMENT '图片',
  `value` varchar(255) DEFAULT '' COMMENT '验证码值',
  `type` varchar(255) DEFAULT 'default' COMMENT '验证码类型',
  `module` varchar(255) DEFAULT 'default' COMMENT '验证码所属业务模块',
  `token` varchar(255) DEFAULT 'default' COMMENT '验证码Token',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime DEFAULT NULL COMMENT '失效时间，null代表永久有效',
  `del_status` int(11) DEFAULT '0' COMMENT '删除状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catptcha
-- ----------------------------

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
-- Table structure for msg_basic_data
-- ----------------------------
DROP TABLE IF EXISTS `msg_basic_data`;
CREATE TABLE `msg_basic_data` (
  `id` bigint(20) NOT NULL,
  `title` bigint(20) DEFAULT NULL,
  `test_time` bigint(20) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_basic_data
-- ----------------------------
INSERT INTO `msg_basic_data` VALUES ('1', null, '1100', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('2', null, '1101', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('3', null, '1101', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('4', null, '1118', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('5', null, '1118', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('6', null, '1119', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('7', null, '1119', null, null, '0');
INSERT INTO `msg_basic_data` VALUES ('8', null, '1120', null, null, '0');

-- ----------------------------
-- Table structure for msg_notify
-- ----------------------------
DROP TABLE IF EXISTS `msg_notify`;
CREATE TABLE `msg_notify` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `content` varchar(1000) DEFAULT NULL COMMENT '消息内容',
  `type` int(11) DEFAULT NULL COMMENT '消息的类型，1: 公告 Announce，2: 提醒 Remind，3：信息 Message',
  `target` bigint(11) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_notify
-- ----------------------------

-- ----------------------------
-- Table structure for msg_notify_read_mark
-- ----------------------------
DROP TABLE IF EXISTS `msg_notify_read_mark`;
CREATE TABLE `msg_notify_read_mark` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '消息的类型，1: 公告 Announce，2: 提醒 Remind，3：信息 Message',
  `read_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_notify_read_mark
-- ----------------------------

-- ----------------------------
-- Table structure for msg_save_remind
-- ----------------------------
DROP TABLE IF EXISTS `msg_save_remind`;
CREATE TABLE `msg_save_remind` (
  `id` bigint(20) NOT NULL,
  `target` bigint(20) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_save_remind
-- ----------------------------

-- ----------------------------
-- Table structure for msg_subscription
-- ----------------------------
DROP TABLE IF EXISTS `msg_subscription`;
CREATE TABLE `msg_subscription` (
  `id` bigint(20) NOT NULL,
  `target` bigint(20) DEFAULT NULL,
  `target_type` varchar(100) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_subscription
-- ----------------------------
INSERT INTO `msg_subscription` VALUES ('1460542385719', '435', 'product', 'comment', '111', '2016-04-13 18:13:06', '2016-04-13 18:13:06', '0');

-- ----------------------------
-- Table structure for msg_subscription_config
-- ----------------------------
DROP TABLE IF EXISTS `msg_subscription_config`;
CREATE TABLE `msg_subscription_config` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `action` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_subscription_config
-- ----------------------------

-- ----------------------------
-- Table structure for msg_user_notify
-- ----------------------------
DROP TABLE IF EXISTS `msg_user_notify`;
CREATE TABLE `msg_user_notify` (
  `id` bigint(20) NOT NULL,
  `is_read` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `notify_id` bigint(20) DEFAULT NULL,
  `notify_type` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `notify_time` datetime DEFAULT NULL COMMENT '公告创建时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_user_notify
-- ----------------------------

-- ----------------------------
-- Table structure for msg_user_notify_copy
-- ----------------------------
DROP TABLE IF EXISTS `msg_user_notify_copy`;
CREATE TABLE `msg_user_notify_copy` (
  `id` bigint(20) NOT NULL,
  `is_read` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `notify_id` bigint(20) DEFAULT NULL,
  `notify_type` int(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `notify_time` datetime DEFAULT NULL COMMENT '公告创建时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_user_notify_copy
-- ----------------------------
INSERT INTO `msg_user_notify_copy` VALUES ('1460376779257', '0', '112', '1460376778209', '3', '2016-04-11 20:12:59', '2016-04-11 20:12:59', null, '0');
INSERT INTO `msg_user_notify_copy` VALUES ('1460376826542', '0', '112', '1460376825880', '3', '2016-04-11 20:13:47', '2016-04-11 20:13:47', null, '0');
INSERT INTO `msg_user_notify_copy` VALUES ('1460542387924', '0', '111', '1460542387193', '2', '2016-04-13 18:13:08', '2016-04-13 18:13:08', '2016-04-13 18:13:07', '0');
INSERT INTO `msg_user_notify_copy` VALUES ('1460542852222', '0', '111', '1460542851982', '2', '2016-04-13 18:20:52', '2016-04-13 18:20:52', '2016-04-13 18:20:52', '0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_USER');
INSERT INTO `role` VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('3', 'ROLE_GUEST');

-- ----------------------------
-- Table structure for spring_trans_user
-- ----------------------------
DROP TABLE IF EXISTS `spring_trans_user`;
CREATE TABLE `spring_trans_user` (
  `id` int(10) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of spring_trans_user
-- ----------------------------

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `Num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1');
INSERT INTO `test` VALUES ('2');
INSERT INTO `test` VALUES ('3');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '1', '2');
INSERT INTO `user_role` VALUES ('3', '2', '1');
INSERT INTO `user_role` VALUES ('4', '3', '1');

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

-- ----------------------------
-- Table structure for zhiyin_community_comment_thumb
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_comment_thumb`;
CREATE TABLE `zhiyin_community_comment_thumb` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhiyin_community_comment_thumb
-- ----------------------------
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52042950033408', '5163', '52042945921024', '2016-08-24 13:23:18', '2016-08-24 13:23:18', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52042950508544', '9960', '52042945921024', '2016-08-24 13:23:18', '2016-08-24 13:23:18', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52042950987776', '5305', '52042948268032', '2016-08-24 13:23:19', '2016-08-24 13:23:19', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52042951843840', '6423', '52042948268032', '2016-08-24 13:23:19', '2016-08-24 13:23:19', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099325063168', '4122', '52099324227584', '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099325562880', '1536', '52099324227584', '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099326242816', '7841', '52099324604416', '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099326738432', '9917', '52099324604416', '2016-08-24 17:12:42', '2016-08-24 17:12:42', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099765350400', '4490', '52099763752960', '2016-08-24 17:14:29', '2016-08-24 17:14:29', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099765919744', '5631', '52099763752960', '2016-08-24 17:14:29', '2016-08-24 17:14:29', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099766325248', '7082', '52099764523008', '2016-08-24 17:14:30', '2016-08-24 17:14:30', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52099766796288', '7791', '52099764523008', '2016-08-24 17:14:30', '2016-08-24 17:14:30', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101128097792', '9466', '52101127258112', '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101128462336', '8847', '52101127258112', '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101129314304', '3811', '52101127585792', '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101129850880', '452', '52101127585792', '2016-08-24 17:20:02', '2016-08-24 17:20:02', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101660229632', '5691', '52101659422720', '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101660659712', '3539', '52101659422720', '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101661110272', '1994', '52101659881472', '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0');
INSERT INTO `zhiyin_community_comment_thumb` VALUES ('52101661421568', '6250', '52101659881472', '2016-08-24 17:22:12', '2016-08-24 17:22:12', '0');

-- ----------------------------
-- Table structure for zhiyin_community_topic_info
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_info`;
CREATE TABLE `zhiyin_community_topic_info` (
  `id` bigint(20) NOT NULL,
  `title` varchar(200) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `media_type` int(11) DEFAULT NULL COMMENT '图片、音频、视频',
  `pics` varchar(1000) DEFAULT NULL COMMENT '图片信息，分号隔开',
  `addr_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `thumb_num` bigint(20) DEFAULT '0',
  `browse_num` int(11) DEFAULT '0',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  `comment_num` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhiyin_community_topic_info
-- ----------------------------
INSERT INTO `zhiyin_community_topic_info` VALUES ('52042922905600', 'test topic[C@19c79ded', null, null, null, null, '1', '1', '2', null, '2016-08-24 13:23:12', '2016-08-24 13:23:12', '1', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52090724515840', 'test topic[C@1ba82161', null, null, null, null, '1', '0', '0', null, '2016-08-24 16:37:42', '2016-08-24 16:37:42', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52090730332160', 'test topic[C@1ba82161', null, null, null, null, '2', '0', '0', null, '2016-08-24 16:37:43', '2016-08-24 16:37:43', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52090731925504', 'test topic[C@1ba82161', null, null, null, null, '3', '0', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52090733096960', 'test topic[C@1ba82161', null, null, null, null, '4', '0', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52090734321664', 'test topic[C@1ba82161', null, null, null, null, '5', '0', '0', null, '2016-08-24 16:37:44', '2016-08-24 16:37:44', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52098784124928', 'test topic[C@2c0f7747', null, null, null, null, '1', '2', '2', null, '2016-08-24 17:10:30', '2016-08-24 17:10:30', '0', '0');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52099303804928', 'test topic[C@5940dc0f', null, null, null, null, '1', '1', '2', null, '2016-08-24 17:12:37', '2016-08-24 17:12:37', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52099742572544', 'test topic[C@4aed58ca', null, null, null, null, '1', '1', '2', null, '2016-08-24 17:14:24', '2016-08-24 17:14:24', '1', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52101107331072', null, null, null, null, null, null, '1', '2', null, '2016-08-24 17:19:57', '2016-08-24 17:19:57', '0', '2');
INSERT INTO `zhiyin_community_topic_info` VALUES ('52101639782400', null, null, null, null, null, null, '1', '2', null, '2016-08-24 17:22:07', '2016-08-24 17:22:07', '0', '2');

-- ----------------------------
-- Table structure for zhiyin_community_topic_media
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_media`;
CREATE TABLE `zhiyin_community_topic_media` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `type` bigint(20) DEFAULT NULL,
  `path` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhiyin_community_topic_media
-- ----------------------------

-- ----------------------------
-- Table structure for zhiyin_community_topic_thumb
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_topic_thumb`;
CREATE TABLE `zhiyin_community_topic_thumb` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhiyin_community_topic_thumb
-- ----------------------------
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52042943565824', '22', '52042922905600', '2016-08-24 13:23:17', '2016-08-24 13:23:17', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52042944466944', '21', '52042922905600', '2016-08-24 13:23:17', '2016-08-24 13:23:17', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52098786041856', '22', '52098784124928', '2016-08-24 17:10:30', '2016-08-24 17:10:30', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52098786611200', '21', '52098784124928', '2016-08-24 17:10:30', '2016-08-24 17:10:30', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52099305496576', '22', '52099303804928', '2016-08-24 17:12:37', '2016-08-24 17:12:37', '1');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52099306049536', '21', '52099303804928', '2016-08-24 17:12:37', '2016-08-24 17:12:37', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52099744296960', '22', '52099742572544', '2016-08-24 17:14:24', '2016-08-24 17:14:24', '1');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52099745386496', '21', '52099742572544', '2016-08-24 17:14:24', '2016-08-24 17:14:24', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52101108748288', '22', '52101107331072', '2016-08-24 17:19:57', '2016-08-24 17:19:57', '1');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52101109260288', '21', '52101107331072', '2016-08-24 17:19:57', '2016-08-24 17:19:57', '0');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52101640556544', '22', '52101639782400', '2016-08-24 17:22:07', '2016-08-24 17:22:07', '1');
INSERT INTO `zhiyin_community_topic_thumb` VALUES ('52101641023488', '21', '52101639782400', '2016-08-24 17:22:07', '2016-08-24 17:22:07', '0');

-- ----------------------------
-- Table structure for zy_ad_allowe_site
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_allowe_site`;
CREATE TABLE `zy_ad_allowe_site` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `ad_id` bigint(20) DEFAULT NULL,
  `addr_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `second_pri` (`ad_id`,`addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zy_ad_allowe_site
-- ----------------------------
INSERT INTO `zy_ad_allowe_site` VALUES ('38022797864960', '38022794207232', '1', '2016-07-15 22:35:10', '2016-07-15 22:35:10', '0');
INSERT INTO `zy_ad_allowe_site` VALUES ('38022798106624', '38022794207232', '2', '2016-07-15 22:35:10', '2016-07-15 22:35:10', '0');
INSERT INTO `zy_ad_allowe_site` VALUES ('38022798376960', '38022794207232', '3', '2016-07-15 22:35:10', '2016-07-15 22:35:10', '0');
INSERT INTO `zy_ad_allowe_site` VALUES ('38024003334144', '38023999201280', '1', '2016-07-15 22:40:04', '2016-07-15 22:40:04', '0');
INSERT INTO `zy_ad_allowe_site` VALUES ('38024003485696', '38023999201280', '2', '2016-07-15 22:40:04', '2016-07-15 22:40:04', '0');
INSERT INTO `zy_ad_allowe_site` VALUES ('38024003620864', '38023999201280', '3', '2016-07-15 22:40:04', '2016-07-15 22:40:04', '0');

-- ----------------------------
-- Table structure for zy_ad_audio_detail
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_audio_detail`;
CREATE TABLE `zy_ad_audio_detail` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `title` varchar(1000) DEFAULT NULL COMMENT '音频标题',
  `ad_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `save_path` varchar(1000) DEFAULT NULL COMMENT '音频路径',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '音频时长',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ad_audio_detail
-- ----------------------------
INSERT INTO `zy_ad_audio_detail` VALUES ('38022797520896', 'test1', '38022794207232', '1', null, '2016-07-15 22:35:10', '2016-07-15 22:35:10', null, '0');
INSERT INTO `zy_ad_audio_detail` VALUES ('38022797697024', 'test1', '38022794207232', '2', null, '2016-07-15 22:35:10', '2016-07-15 22:35:10', null, '0');
INSERT INTO `zy_ad_audio_detail` VALUES ('38024001818624', 'test1', '38023999201280', '1', null, '2016-07-15 22:40:04', '2016-07-15 22:40:04', null, '0');
INSERT INTO `zy_ad_audio_detail` VALUES ('38024003031040', 'test1', '38023999201280', '2', null, '2016-07-15 22:40:04', '2016-07-15 22:40:04', null, '0');

-- ----------------------------
-- Table structure for zy_ad_baisc_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_baisc_info`;
CREATE TABLE `zy_ad_baisc_info` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `company_id` bigint(20) DEFAULT NULL,
  `title` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shelf_on_time` datetime DEFAULT NULL,
  `shelf_off_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `shelf_status` int(11) DEFAULT '1' COMMENT '1未上架；2已上架；3已下架',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of zy_ad_baisc_info
-- ----------------------------
INSERT INTO `zy_ad_baisc_info` VALUES ('39297321934848', '1', '∂sss€', '2016-07-19 13:01:13', '2016-07-19 13:04:13', '2016-07-19 13:01:13', '2016-07-19 13:01:13', null, '1', '0');
INSERT INTO `zy_ad_baisc_info` VALUES ('46396960788480', '1', '∂sss9294', '2016-08-08 14:29:43', '2016-08-08 14:32:43', '2016-08-08 14:29:43', '2016-08-08 14:29:43', null, '1', '0');
INSERT INTO `zy_ad_baisc_info` VALUES ('46408095408128', '1', '∂sss??', '2016-08-08 15:15:01', '2016-08-08 15:18:01', '2016-08-08 15:15:01', '2016-08-08 15:15:01', null, '1', '0');
INSERT INTO `zy_ad_baisc_info` VALUES ('46410176380928', '1', '∂sss??sd', '2016-08-08 15:23:29', '2016-08-08 15:26:29', '2016-08-08 15:23:29', '2016-08-08 15:23:29', null, '1', '0');

-- ----------------------------
-- Table structure for zy_ourchat_broder_dialog
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_broder_dialog`;
CREATE TABLE `zy_ourchat_broder_dialog` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT '0' COMMENT '0未读 1已读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_broder_dialog
-- ----------------------------

-- ----------------------------
-- Table structure for zy_ourchat_broder_dialog_pull
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_broder_dialog_pull`;
CREATE TABLE `zy_ourchat_broder_dialog_pull` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `pull_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_broder_dialog_pull
-- ----------------------------

-- ----------------------------
-- Table structure for zy_ourchat_dialog_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_info`;
CREATE TABLE `zy_ourchat_dialog_info` (
  `id` bigint(20) NOT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_dialog_info
-- ----------------------------
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033017032704', '1', '2', 'hello, user1 talk to usxF0\\x9F\\x92\\x94er2.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033017987072', '1', '3', 'hello, user1 talk to user3.\\uF09F9294', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033021476864', '3', '1', 'hello, user3 talk to user1.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034166738944', '1', '2', 'hello, user1 talk to user2.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034167607296', '1', '3', 'hello, user1 talk to user3.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034171678720', '3', '1', 'hello, user3 talk to user1.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51429251485696', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:46:09', '2016-08-22 19:46:09', '2016-08-22 19:46:09', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51429783764992', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:48:19', '2016-08-22 19:48:19', '2016-08-22 19:48:19', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430070153216', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:49:29', '2016-08-22 19:49:29', '2016-08-22 19:49:29', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430380208128', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:50:45', '2016-08-22 19:50:45', '2016-08-22 19:50:45', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430381760512', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 19:50:45', '2016-08-22 19:50:45', '2016-08-22 19:50:45', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430383771648', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 19:50:46', '2016-08-22 19:50:46', '2016-08-22 19:50:46', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430784610304', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:52:24', '2016-08-22 19:52:24', '2016-08-22 19:52:24', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430786744320', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 19:52:24', '2016-08-22 19:52:24', '2016-08-22 19:52:24', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430788235264', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 19:52:25', '2016-08-22 19:52:25', '2016-08-22 19:52:25', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430880784384', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 19:52:47', '2016-08-22 19:52:47', '2016-08-22 19:52:47', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430881873920', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 19:52:47', '2016-08-22 19:52:47', '2016-08-22 19:52:47', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51430883139584', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 19:52:48', '2016-08-22 19:52:48', '2016-08-22 19:52:48', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432694890496', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432696389632', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432698155008', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:00:11', '2016-08-22 20:00:11', '2016-08-22 20:00:11', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432803123200', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:00:36', '2016-08-22 20:00:36', '2016-08-22 20:00:36', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432804573184', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 20:00:37', '2016-08-22 20:00:37', '2016-08-22 20:00:37', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51432805851136', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:00:37', '2016-08-22 20:00:37', '2016-08-22 20:00:37', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51433670672384', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51433672081408', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51433673637888', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('51735210291200', '10', '20', 'hello, user1 talk to user2.', '2016-08-23 16:31:05', '2016-08-23 16:31:05', '2016-08-23 16:31:05', '0');

-- ----------------------------
-- Table structure for zy_ourchat_dialog_latest
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_latest`;
CREATE TABLE `zy_ourchat_dialog_latest` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL COMMENT '聊天对象',
  `partner_name` varchar(100) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_dialog_latest
-- ----------------------------
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51433669279744', '1', '2', null, '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:04:08', '0', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51433669816320', '2', '1', null, '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:04:08', '0', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51433672536064', '3', '1', null, '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51433673039872', '1', '3', null, '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51735204999168', '10', '20', null, '10', '20', 'hello, user1 talk to user2.', '2016-08-23 16:31:05', '0', '2016-08-23 16:31:05', '2016-08-23 16:31:05', '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('51735207071744', '20', '10', null, '10', '20', 'hello, user1 talk to user2.', '2016-08-23 16:31:05', '0', '2016-08-23 16:31:06', '2016-08-23 16:31:06', '0');

-- ----------------------------
-- Table structure for zy_ourchat_dialog_record
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_record`;
CREATE TABLE `zy_ourchat_dialog_record` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `sender` bigint(20) DEFAULT NULL,
  `receiver` bigint(20) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `is_read` int(11) DEFAULT '0' COMMENT '0 已读 1 未读',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `s_p` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_dialog_record
-- ----------------------------
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433670004736', '2', '1', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:04:08', '0', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433670172672', '1', '2', '1', '2', 'hello, user1 talk to user2.', '2016-08-22 20:04:08', '0', '2016-08-22 20:04:08', '2016-08-22 20:04:08', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433671725056', '3', '1', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433671901184', '1', '3', '1', '3', 'hello, user1 talk to user3.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433673220096', '1', '3', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51433673404416', '3', '1', '3', '1', 'hello, user3 talk to user1.', '2016-08-22 20:04:09', '0', '2016-08-22 20:04:09', '2016-08-22 20:04:09', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51735208583168', '20', '10', '10', '20', 'hello, user1 talk to user2.', '2016-08-23 16:31:05', '0', '2016-08-23 16:31:06', '2016-08-23 16:31:06', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('51735209975808', '10', '20', '10', '20', 'hello, user1 talk to user2.', '2016-08-23 16:31:05', '0', '2016-08-23 16:31:06', '2016-08-23 16:31:06', '0');

-- ----------------------------
-- Table structure for zy_ourchat_dialog_status
-- ----------------------------
DROP TABLE IF EXISTS `zy_ourchat_dialog_status`;
CREATE TABLE `zy_ourchat_dialog_status` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '消息所属用户编号',
  `partner_id` bigint(20) DEFAULT NULL,
  `unread_num` int(11) DEFAULT '0' COMMENT '未读数量',
  `read_time` datetime DEFAULT NULL COMMENT '阅读时间',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `un` (`user_id`,`partner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_dialog_status
-- ----------------------------
INSERT INTO `zy_ourchat_dialog_status` VALUES ('51432695074816', '2', '1', '3', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '2016-08-22 20:00:10', '0');
INSERT INTO `zy_ourchat_dialog_status` VALUES ('51432696659968', '3', '1', '3', '2016-08-22 20:00:11', '2016-08-22 20:00:11', '2016-08-22 20:00:11', '0');
INSERT INTO `zy_ourchat_dialog_status` VALUES ('51432698380288', '1', '3', '0', '2016-08-22 20:04:09', '2016-08-22 20:00:11', '2016-08-22 20:00:11', '0');
INSERT INTO `zy_ourchat_dialog_status` VALUES ('51735210967040', '20', '10', '1', '2016-08-23 16:31:07', '2016-08-23 16:31:07', '2016-08-23 16:31:07', '0');
