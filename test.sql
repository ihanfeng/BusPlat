/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-07-12 23:08:25
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

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
