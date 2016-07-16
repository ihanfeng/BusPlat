/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-07-16 11:49:37
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
  `del_status` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL COMMENT '音频时长',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zy_ad_audio_detail
-- ----------------------------
INSERT INTO `zy_ad_audio_detail` VALUES ('38022797520896', 'test1', '38022794207232', '1', null, '2016-07-15 22:35:10', '2016-07-15 22:35:10', '0', null);
INSERT INTO `zy_ad_audio_detail` VALUES ('38022797697024', 'test1', '38022794207232', '2', null, '2016-07-15 22:35:10', '2016-07-15 22:35:10', '0', null);
INSERT INTO `zy_ad_audio_detail` VALUES ('38024001818624', 'test1', '38023999201280', '1', null, '2016-07-15 22:40:04', '2016-07-15 22:40:04', '0', null);
INSERT INTO `zy_ad_audio_detail` VALUES ('38024003031040', 'test1', '38023999201280', '2', null, '2016-07-15 22:40:04', '2016-07-15 22:40:04', '0', null);

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
INSERT INTO `zy_ad_baisc_info` VALUES ('38022794207232', '1', null, '2016-07-15 22:35:09', '2016-07-15 22:38:09', '2016-07-15 22:35:09', '2016-07-15 22:35:09', null, '1', '0');
INSERT INTO `zy_ad_baisc_info` VALUES ('38023999201280', '1', null, '2016-07-15 22:40:03', '2016-07-15 22:43:03', '2016-07-15 22:40:03', '2016-07-15 22:40:03', null, '1', '0');

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
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033017032704', '1', '2', 'hello, user1 talk to user2.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033017987072', '1', '3', 'hello, user1 talk to user3.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38033021476864', '3', '1', 'hello, user3 talk to user1.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034166738944', '1', '2', 'hello, user1 talk to user2.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034167607296', '1', '3', 'hello, user1 talk to user3.', null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_info` VALUES ('38034171678720', '3', '1', 'hello, user3 talk to user1.', null, null, null, '0');

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
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('38034163064832', '1', '2', null, '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('38034166108160', '2', '1', null, '1', '2', 'hello, user1 talk to user2.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('38034168012800', '3', '1', null, '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');
INSERT INTO `zy_ourchat_dialog_latest` VALUES ('38034168647680', '1', '3', null, '3', '1', 'hello, user3 talk to user1.', null, null, null, null, '0');

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
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034166243328', '2', '1', '1', '2', 'hello, user1 talk to user2.', null, null, '2016-07-15 23:21:25', '2016-07-15 23:21:25', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034166607872', '1', '2', '1', '2', 'hello, user1 talk to user2.', null, null, '2016-07-15 23:21:25', '2016-07-15 23:21:25', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034167336960', '3', '1', '1', '3', 'hello, user1 talk to user3.', null, null, '2016-07-15 23:21:25', '2016-07-15 23:21:25', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034167468032', '1', '3', '1', '3', 'hello, user1 talk to user3.', null, null, '2016-07-15 23:21:25', '2016-07-15 23:21:25', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034171076608', '1', '3', '3', '1', 'hello, user3 talk to user1.', null, null, '2016-07-15 23:21:26', '2016-07-15 23:21:26', '0');
INSERT INTO `zy_ourchat_dialog_record` VALUES ('38034171547648', '3', '1', '3', '1', 'hello, user3 talk to user1.', null, null, '2016-07-15 23:21:26', '2016-07-15 23:21:26', '0');
