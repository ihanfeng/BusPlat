/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-09-30 11:31:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zy_common_obj_view
-- ----------------------------
DROP TABLE IF EXISTS `zy_common_obj_view`;
CREATE TABLE `zy_common_obj_view` (
  `id` bigint(20) DEFAULT NULL,
  `obj_id` bigint(11) NOT NULL,
  `pond` tinyint(4) NOT NULL COMMENT '池子，就是用来随机用的',
  `view` int(11) NOT NULL,
  PRIMARY KEY (`obj_id`,`pond`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
