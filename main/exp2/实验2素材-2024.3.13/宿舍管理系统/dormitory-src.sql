/*
 Navicat MySQL Data Transfer

 Source Server         : dormitory_mysql
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : student1

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 18/03/2023 20:03:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `user` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('jrp', '123456');

-- ----------------------------
-- Table structure for change_stay
-- ----------------------------
DROP TABLE IF EXISTS `change_stay`;
CREATE TABLE `change_stay`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `days` int NULL DEFAULT NULL,
  `need_money` float NULL DEFAULT NULL,
  `paid_money` float NULL DEFAULT NULL,
  `diff_money` float NULL DEFAULT NULL,
  `extend` int NULL DEFAULT 0,
  INDEX `sno`(`sno`) USING BTREE,
  CONSTRAINT `change_stay_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of change_stay
-- ----------------------------

-- ----------------------------
-- Table structure for out_room
-- ----------------------------
DROP TABLE IF EXISTS `out_room`;
CREATE TABLE `out_room`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `out_time` datetime NULL DEFAULT NULL,
  `reason` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `need_money` float NULL DEFAULT NULL,
  `paid_money` float NULL DEFAULT NULL,
  `diff_money` float NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of out_room
-- ----------------------------
INSERT INTO `out_room` VALUES ('M03', '2022-08-19 00:00:00', NULL, 0, 0, 0);

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pay_money` float NULL DEFAULT NULL,
  `pay_time` datetime NULL DEFAULT NULL,
  `need_money` float NULL DEFAULT NULL,
  `paid_money` float NULL DEFAULT NULL,
  `diff_money` float NULL DEFAULT NULL,
  INDEX `sno`(`sno`) USING BTREE,
  CONSTRAINT `pay_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES ('W01', 500, '2022-08-19 00:00:00', 500, 400, 100);
INSERT INTO `pay` VALUES ('W02', 500, '2022-08-19 00:00:00', 500, 100, 400);
INSERT INTO `pay` VALUES ('M01', 500, '2022-08-19 00:00:00', 200, 100, 100);
INSERT INTO `pay` VALUES ('M02', 500, '2022-08-19 00:00:00', 500, 300, 200);
INSERT INTO `pay` VALUES ('M03', 500, '2022-08-19 00:00:00', 0, 200, 0);
INSERT INTO `pay` VALUES ('M04', 500, '2022-08-19 00:00:00', 500, 300, 200);
INSERT INTO `pay` VALUES ('M03', 500, '2022-08-20 00:00:00', 500, 300, 200);

-- ----------------------------
-- Table structure for replace_room
-- ----------------------------
DROP TABLE IF EXISTS `replace_room`;
CREATE TABLE `replace_room`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_rno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `new_bedno` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `chane_time` datetime NULL DEFAULT NULL,
  INDEX `sno`(`sno`) USING BTREE,
  CONSTRAINT `replace_room_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of replace_room
-- ----------------------------
INSERT INTO `replace_room` VALUES ('W01', 'W02', '1', '2022-08-19 00:00:00');
INSERT INTO `replace_room` VALUES ('M04', 'M02', '1', '2022-08-20 00:00:00');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `rno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `rsex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `sum` int NULL DEFAULT NULL,
  PRIMARY KEY (`rno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('M01', '男', 2);
INSERT INTO `room` VALUES ('M02', '男', 4);
INSERT INTO `room` VALUES ('W01', '女', 2);
INSERT INTO `room` VALUES ('W02', '女', 4);
INSERT INTO `room` VALUES ('W03', '女', 4);

-- ----------------------------
-- Table structure for stay_room
-- ----------------------------
DROP TABLE IF EXISTS `stay_room`;
CREATE TABLE `stay_room`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rno` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `bedno` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `intime` datetime NULL DEFAULT NULL,
  `days` int NULL DEFAULT NULL,
  `outtime` datetime NULL DEFAULT NULL,
  `remark` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `out_room` int NULL DEFAULT 0,
  INDEX `sno`(`sno`) USING BTREE,
  INDEX `rno`(`rno`) USING BTREE,
  CONSTRAINT `stay_room_ibfk_1` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `stay_room_ibfk_2` FOREIGN KEY (`rno`) REFERENCES `room` (`rno`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stay_room
-- ----------------------------
INSERT INTO `stay_room` VALUES ('W01', 'W02', '1', '2022-08-19 00:00:00', 100, '2022-11-27 00:00:00', NULL, 0);
INSERT INTO `stay_room` VALUES ('W02', 'W01', '2', '2022-08-19 00:00:00', 100, '2022-11-27 00:00:00', NULL, 0);
INSERT INTO `stay_room` VALUES ('M01', 'M01', '1', '2022-08-19 00:00:00', 100, '2022-08-19 00:00:00', NULL, 0);
INSERT INTO `stay_room` VALUES ('M02', 'M01', '2', '2022-08-19 00:00:00', 100, '2022-11-27 00:00:00', NULL, 0);
INSERT INTO `stay_room` VALUES ('M03', 'M02', '2', '2022-08-19 00:00:00', 100, '2022-11-27 00:00:00', NULL, 1);
INSERT INTO `stay_room` VALUES ('M04', 'M02', '1', '2022-08-19 00:00:00', 100, '2022-11-27 00:00:00', NULL, 0);
INSERT INTO `stay_room` VALUES ('M03', 'M02', '2', '2022-08-20 00:00:00', 100, '2022-11-28 00:00:00', NULL, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `sno` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sname` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ssex` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('M01', 'm1', '12', '男');
INSERT INTO `student` VALUES ('M02', 'm2', '123', '男');
INSERT INTO `student` VALUES ('M03', 'm3', '123', '男');
INSERT INTO `student` VALUES ('M04', 'm4', '1231', '男');
INSERT INTO `student` VALUES ('M05', 'm01', '123**', '男');
INSERT INTO `student` VALUES ('W01', 'w1', '123', '女');
INSERT INTO `student` VALUES ('W02', 'w2', '12', '女');

SET FOREIGN_KEY_CHECKS = 1;
