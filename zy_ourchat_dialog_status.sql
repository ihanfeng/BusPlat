/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-08-22 20:29:20
*/

SET FOREIGN_KEY_CHECKS=0;

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
