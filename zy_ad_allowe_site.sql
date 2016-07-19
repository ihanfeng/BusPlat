/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-07-19 21:31:06
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
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `second_pri` (`ad_id`,`addr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  `remark` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `shelf_status` int(11) DEFAULT '1' COMMENT '1未上架；2已上架；3已下架',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
