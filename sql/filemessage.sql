/*
 Navicat Premium Data Transfer

 Source Server         : Demo
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : javaexam

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/05/2022 23:08:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for filemessage
-- ----------------------------
DROP TABLE IF EXISTS `filemessage`;
CREATE TABLE `filemessage`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '文件编号',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实文件名',
  `path_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '存放文件名（路径）',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '文件上传时间',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小',
  `filetype_id` int NULL DEFAULT NULL COMMENT '文件类型编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `file_type`(`filetype_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of filemessage
-- ----------------------------
INSERT INTO `filemessage` VALUES (26, 'bg.jpg', 'C:\\maven_courses\\Experience\\web\\pic\\bg.jpg', '2021-12-19 04:55:15', 0.06, 1);
INSERT INTO `filemessage` VALUES (27, 'bg2.png', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\bg2.png', '2021-12-19 04:58:00', 0.05, 2);
INSERT INTO `filemessage` VALUES (28, 'bg3.jpg', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\bg3.jpg', '2021-12-19 04:58:06', 0.96, 1);
INSERT INTO `filemessage` VALUES (29, 'bg4.png', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\bg4.png', '2021-12-19 04:58:14', 0.02, 2);
INSERT INTO `filemessage` VALUES (30, 'background.jpg', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\background.jpg', '2021-12-19 07:06:50', 0.05, 1);
INSERT INTO `filemessage` VALUES (31, 'pic1.JPG', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\pic1.JPG', '2021-12-19 07:08:21', 0.03, 1);
INSERT INTO `filemessage` VALUES (32, 'pic2.png', 'C:\\maven_courses\\Exam\\examDemo\\web\\pic\\pic2.png', '2021-12-21 02:28:21', 0.04, 2);
INSERT INTO `filemessage` VALUES (34, 'pic4.JPG', 'C:\\maven_courses\\exp\\fileUpLoad\\src\\main\\webapp\\pic\\pic4.JPG', '2022-05-09 14:49:37', 0.25, 1);
INSERT INTO `filemessage` VALUES (35, 'pic5.png', 'C:\\maven_courses\\exp\\fileUpLoad\\src\\main\\webapp\\pic\\pic5.png', '2022-05-09 14:58:54', 0.06, 2);

SET FOREIGN_KEY_CHECKS = 1;
