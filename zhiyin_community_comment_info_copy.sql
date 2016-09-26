/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-09-26 19:33:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zhiyin_community_comment_info_copy
-- ----------------------------
DROP TABLE IF EXISTS `zhiyin_community_comment_info_copy`;
CREATE TABLE `zhiyin_community_comment_info_copy` (
  `id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `comment` varchar(1000) CHARACTER SET utf8mb4 DEFAULT NULL,
  `thumb_num` int(11) DEFAULT '0',
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '插入数据库时间',
  `update_time` datetime DEFAULT NULL,
  `del_status` int(11) DEFAULT '0',
  `reply_user_id` bigint(20) DEFAULT NULL COMMENT '评论回复的用户',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '评论的目标id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
