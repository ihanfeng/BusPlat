/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-07-13 22:35:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
  `del_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ad_allowe_site
-- ----------------------------

-- ----------------------------
-- Table structure for zy_ad_audio_detail
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_audio_detail`;
CREATE TABLE `zy_ad_audio_detail` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `role_id` bigint(20) DEFAULT NULL,
  `save_path` varchar(1000) DEFAULT NULL COMMENT '音频路径',
  `title` varchar(1000) DEFAULT NULL COMMENT '音频标题',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '音频时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ad_audio_detail
-- ----------------------------

-- ----------------------------
-- Table structure for zy_ad_baisc_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_ad_baisc_info`;
CREATE TABLE `zy_ad_baisc_info` (
  `id` bigint(20) NOT NULL COMMENT '音频广告',
  `company_id` bigint(20) DEFAULT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `shelf_on_time` datetime DEFAULT NULL,
  `shelf_off_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `shelf_status` int(11) DEFAULT '1' COMMENT '1未上架；2已上架；3已下架',
  `del_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ad_baisc_info
-- ----------------------------

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
  `is_read` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
  `del_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
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
INSERT INTO `zy_ourchat_dialog_info` VALUES ('36796134776832', '1', '2', 'hello, user1 talk to user2.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('36796135723008', '1', '3', 'hello, user1 talk to user3.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('36796137082880', '3', '1', 'hello, user3 talk to user1.', null, null, null, '0');

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
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('36796133486592', '1', '2', null, '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('36796134043648', '2', '1', null, '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('36796136087552', '3', '1', null, '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('36796136398848', '1', '3', null, '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');

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
  `is_read` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ourchat_dialog_record
-- ----------------------------
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796134244352', '2', '1', '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796134502400', '1', '2', '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796135366656', '3', '1', '1', '3', 'hello, user1 talk to user3.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796135542784', '1', '3', '1', '3', 'hello, user1 talk to user3.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796136677376', '1', '3', '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('36796136898560', '3', '1', '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');
