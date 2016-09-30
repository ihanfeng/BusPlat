/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-09-30 11:10:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zy_device_fix_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_fix_info`;
CREATE TABLE `zy_device_fix_info` (
  `id` bigint(20) NOT NULL COMMENT '设备固定信息',
  `serialno` varchar(1000) DEFAULT NULL,
  `imei` varchar(1000) DEFAULT NULL,
  `uuid` varchar(1000) DEFAULT NULL COMMENT '设备唯一标识符',
  `model` varchar(1000) DEFAULT NULL,
  `idfa` varchar(1000) DEFAULT NULL COMMENT '广告位标识符idfa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_device_mov_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_mov_info`;
CREATE TABLE `zy_device_mov_info` (
  `id` bigint(20) NOT NULL COMMENT '设备移动信息',
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备编号',
  `loc_lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `loc_lon` varchar(1000) DEFAULT NULL COMMENT '经度',
  `loc_coord` varchar(100) DEFAULT NULL COMMENT '坐标系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zy_device_var_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_var_info`;
CREATE TABLE `zy_device_var_info` (
  `id` bigint(20) NOT NULL COMMENT '设备可变信息',
  `system_name` varchar(1000) DEFAULT NULL COMMENT '系统名称',
  `system_version` varchar(1000) DEFAULT NULL COMMENT '系统版本号',
  `app_name` varchar(255) DEFAULT NULL COMMENT 'App的名称',
  `app_version` varchar(1000) DEFAULT NULL COMMENT 'App的版本号',
  `app_build_version` varchar(1000) DEFAULT NULL COMMENT 'App build版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
