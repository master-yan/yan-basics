/*
 Navicat Premium Data Transfer

 Source Server         : Test
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 127.0.0.1:3306
 Source Schema         : master-yan

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 01/09/2020 16:39:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_book
-- ----------------------------
DROP TABLE IF EXISTS `test_book`;
CREATE TABLE `test_book`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '书名',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `total` int(11) NULL DEFAULT NULL COMMENT '总数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `creation_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for test_cup
-- ----------------------------
DROP TABLE IF EXISTS `test_cup`;
CREATE TABLE `test_cup`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `cup_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '杯子名称',
  `total` int(11) NULL DEFAULT NULL COMMENT '总数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `introduction` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '介绍',
  `creation_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
