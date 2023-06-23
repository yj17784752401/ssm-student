/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : ssm_student

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2023-06-23 19:54:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '''主键''',
  `admin_name` varchar(50) NOT NULL DEFAULT '' COMMENT '''登录用户名''',
  `pwd` varchar(50) NOT NULL DEFAULT '' COMMENT '''密码''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(20) NOT NULL COMMENT '学号',
  `real_name` varchar(20) NOT NULL COMMENT '学号',
  `birth_day` date NOT NULL COMMENT '出生日期',
  `phone` varchar(15) NOT NULL COMMENT '联系电话',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `photo_path` varchar(200) NOT NULL DEFAULT './resources/imgs/plus.png' COMMENT '个人免冠照存放路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_no` (`no`) USING BTREE,
  UNIQUE KEY `uk_phone` (`phone`) USING BTREE,
  UNIQUE KEY `uk_email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
