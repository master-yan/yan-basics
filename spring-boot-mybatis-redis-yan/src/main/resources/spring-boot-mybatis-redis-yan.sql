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

 Date: 04/09/2020 16:27:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_sweet
-- ----------------------------
DROP TABLE IF EXISTS `test_sweet`;
CREATE TABLE `test_sweet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sweet_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '糖果名称',
  `brand` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '品牌',
  `total` int(11) NULL DEFAULT NULL COMMENT '总数量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '售价',
  `creation_time` timestamp(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updated_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;