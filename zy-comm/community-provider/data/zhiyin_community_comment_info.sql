/*
Navicat MySQL Data Transfer

Source Server         : 123.57.230.238
Source Server Version : 50625
Source Host           : 123.57.230.238:3306
Source Database       : zhiyin

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2016-08-23 07:35:55
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
