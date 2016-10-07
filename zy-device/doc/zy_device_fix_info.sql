/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : zhiyin

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2016-10-07 11:55:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for zy_device_fix_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_fix_info`;
CREATE TABLE `zy_device_fix_info` (
  `id` bigint(20) NOT NULL COMMENT '序号',
  `serialno` varchar(1000) DEFAULT NULL,
  `imei` varchar(1000) DEFAULT NULL,
  `uuid` varchar(1000) DEFAULT NULL COMMENT '设备唯一标识符',
  `model` varchar(1000) DEFAULT NULL COMMENT '设备的型号, (iPhone 6,iPad 2 (32nm) 等等)',
  `idfa` varchar(1000) DEFAULT NULL COMMENT '广告位标识符idfa',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备固定信息，一般不会更改，用于确定设备唯一性。';

-- ----------------------------
-- Table structure for zy_device_mov_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_mov_info`;
CREATE TABLE `zy_device_mov_info` (
  `id` bigint(20) NOT NULL,
  `device_id` bigint(20) DEFAULT NULL COMMENT '设备编号',
  `loc_lat` varchar(255) DEFAULT NULL COMMENT '纬度',
  `loc_lon` varchar(1000) DEFAULT NULL COMMENT '经度',
  `loc_coord` varchar(100) DEFAULT NULL COMMENT '坐标系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备移动信息，设备会接入不同网络，不断改变位置。';

-- ----------------------------
-- Table structure for zy_device_var_info
-- ----------------------------
DROP TABLE IF EXISTS `zy_device_var_info`;
CREATE TABLE `zy_device_var_info` (
  `id` bigint(20) NOT NULL,
  `system_name` varchar(1000) DEFAULT NULL COMMENT '系统名称',
  `system_version` varchar(1000) DEFAULT NULL COMMENT '系统版本号',
  `app_name` varchar(255) DEFAULT NULL COMMENT 'App的名称',
  `app_version` varchar(1000) DEFAULT NULL COMMENT 'App的版本号',
  `app_build_version` varchar(1000) DEFAULT NULL COMMENT 'App build版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备可变信息，比如系统版本会随着系统升级改变';
