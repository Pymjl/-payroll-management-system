/*
 Navicat Premium Data Transfer

 Source Server         : mysql02
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : pymjl.com:3311
 Source Schema         : payroll_system

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/06/2022 21:30:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_attendance_amount
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance_amount`;
CREATE TABLE `t_attendance_amount`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '奖罚明细 1.全勤 2.早退 3.缺勤 ',
  `attendance_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出勤描述',
  `amount` int(0) NOT NULL COMMENT '奖罚金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attendance_amount
-- ----------------------------
INSERT INTO `t_attendance_amount` VALUES (1, '全勤', 300);
INSERT INTO `t_attendance_amount` VALUES (2, '早退', -50);
INSERT INTO `t_attendance_amount` VALUES (3, '缺勤', -200);

-- ----------------------------
-- Table structure for t_attendance_personal
-- ----------------------------
DROP TABLE IF EXISTS `t_attendance_personal`;
CREATE TABLE `t_attendance_personal`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '个人出勤表',
  `user_id` bigint(0) NOT NULL COMMENT '用户信息id',
  `faculty_id` bigint(0) NOT NULL COMMENT '所属系id',
  `department_id` bigint(0) NOT NULL COMMENT '所属部门id',
  `working_hours` int(0) NULL DEFAULT NULL COMMENT '工作时长',
  `status` int(0) NULL DEFAULT NULL COMMENT '工作状态（-1早退、0请假（缺勤）、1正常）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间（签到）',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间（签退）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attendance_personal
-- ----------------------------
INSERT INTO `t_attendance_personal` VALUES (1, 1, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (2, 2, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (3, 3, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (4, 4, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (5, 5, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (6, 6, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (7, 7, 1, 1, 8, 1, '2022-06-08 18:44:10', '2022-06-08 18:44:10');
INSERT INTO `t_attendance_personal` VALUES (8, 8, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (9, 9, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (10, 10, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (11, 11, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (12, 12, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (13, 13, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (14, 14, 1, 2, 8, 1, '2022-06-08 18:45:41', '2022-06-08 18:45:41');
INSERT INTO `t_attendance_personal` VALUES (15, 15, 1, 3, 8, 1, '2022-06-08 18:47:20', '2022-06-08 18:47:20');
INSERT INTO `t_attendance_personal` VALUES (16, 16, 1, 3, 8, 1, '2022-06-08 18:47:20', '2022-06-08 18:47:20');
INSERT INTO `t_attendance_personal` VALUES (17, 17, 1, 3, 8, 1, '2022-06-08 18:47:20', '2022-06-08 18:47:20');
INSERT INTO `t_attendance_personal` VALUES (18, 18, 1, 3, 8, 1, '2022-06-08 18:47:20', '2022-06-08 18:47:20');
INSERT INTO `t_attendance_personal` VALUES (19, 19, 1, 3, 8, 1, '2022-06-08 18:47:21', '2022-06-08 18:47:21');
INSERT INTO `t_attendance_personal` VALUES (20, 20, 1, 3, 8, 1, '2022-06-08 18:47:21', '2022-06-08 18:47:21');
INSERT INTO `t_attendance_personal` VALUES (21, 34, 1, 3, 8, 1, '2022-06-08 18:47:21', '2022-06-08 18:47:21');
INSERT INTO `t_attendance_personal` VALUES (22, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (23, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (24, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (25, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (26, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (27, 34, 1, 4, 8, 1, '2022-06-08 18:49:58', '2022-06-08 18:49:58');
INSERT INTO `t_attendance_personal` VALUES (28, 34, 1, 5, 8, 1, '2022-06-08 18:54:42', '2022-06-08 18:54:42');
INSERT INTO `t_attendance_personal` VALUES (29, 34, 1, 5, 8, 1, '2022-06-08 18:54:42', '2022-06-08 18:54:42');
INSERT INTO `t_attendance_personal` VALUES (30, 34, 1, 5, 8, 1, '2022-06-08 18:54:42', '2022-06-08 18:54:42');
INSERT INTO `t_attendance_personal` VALUES (31, 31, 1, 5, 8, 1, '2022-06-08 18:54:43', '2022-06-08 18:54:43');
INSERT INTO `t_attendance_personal` VALUES (32, 34, 1, 5, 3, -1, '2022-06-08 19:32:30', '2022-06-08 23:08:19');
INSERT INTO `t_attendance_personal` VALUES (33, 34, 1, 5, 0, -1, '2022-06-08 19:40:29', '2022-06-08 19:40:34');
INSERT INTO `t_attendance_personal` VALUES (34, 34, 1, 5, 2, 1, '2022-06-09 01:44:55', '2022-06-09 03:48:37');
INSERT INTO `t_attendance_personal` VALUES (35, 30, 1, 1, NULL, 1, '2022-06-09 12:47:47', '2022-06-09 12:47:47');
INSERT INTO `t_attendance_personal` VALUES (37, 31, 1, 6, 0, -1, '2022-06-09 14:49:58', '2022-06-09 14:50:04');

-- ----------------------------
-- Table structure for t_basic_wage
-- ----------------------------
DROP TABLE IF EXISTS `t_basic_wage`;
CREATE TABLE `t_basic_wage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '职位 1普通员工 2部门主管',
  `manager_wage` int(0) NOT NULL COMMENT '部门经理基本工资',
  `common_wage` int(0) NOT NULL COMMENT '普通员工基本工资',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间\r\n更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_basic_wage
-- ----------------------------
INSERT INTO `t_basic_wage` VALUES (1, 9000, 6000, '2022-06-08 20:29:13', '2022-06-09 01:20:01');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `department_id` int(0) NOT NULL COMMENT '部门的编号',
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '部门的名字',
  `department_num` int(0) NULL DEFAULT NULL COMMENT '部门员工总数',
  `department_boss_id` int(0) NOT NULL COMMENT '部门老大的编号',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, 1, '办公室', 7, 34, '2022-06-08 22:02:42', '2022-06-08 22:02:42');
INSERT INTO `t_department` VALUES (2, 2, '纪委办公室', 8, 22, '2022-06-08 22:03:17', '2022-06-08 22:03:17');
INSERT INTO `t_department` VALUES (3, 3, '人事处', 7, 15, '2022-06-08 22:03:56', '2022-06-08 22:03:56');
INSERT INTO `t_department` VALUES (4, 4, '教务处', 6, 31, '2022-06-07 21:06:07', '2022-06-07 21:06:07');
INSERT INTO `t_department` VALUES (5, 5, '学生处', 4, 28, '2022-06-07 21:06:41', '2022-06-07 21:06:41');

-- ----------------------------
-- Table structure for t_department_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_department_staff`;
CREATE TABLE `t_department_staff`  (
  `id` int(0) NOT NULL COMMENT 'id自增',
  `staff_id` int(0) NOT NULL COMMENT '员工的编号',
  `staff_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工的名字',
  `staff_position` int(0) NULL DEFAULT NULL COMMENT '员工的职位',
  `staff_department_id` int(0) NULL DEFAULT NULL COMMENT '员工所属部门的编号',
  `staff_department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工所属部门的名字',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '关系时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department_staff
-- ----------------------------
INSERT INTO `t_department_staff` VALUES (1, 1, 'test0', 0, 1, '办公室', '2022-06-07 20:40:46', '2022-06-07 20:40:46');
INSERT INTO `t_department_staff` VALUES (2, 2, 'test1', 0, 1, '办公室', '2022-06-07 20:40:46', '2022-06-07 20:40:46');
INSERT INTO `t_department_staff` VALUES (3, 3, 'test2', 0, 1, '办公室', '2022-06-07 20:40:47', '2022-06-07 20:40:47');
INSERT INTO `t_department_staff` VALUES (4, 4, 'test3', 0, 1, '办公室', '2022-06-07 20:40:47', '2022-06-07 20:40:47');
INSERT INTO `t_department_staff` VALUES (5, 5, 'test4', 0, 1, '办公室', '2022-06-07 20:40:47', '2022-06-07 20:40:47');
INSERT INTO `t_department_staff` VALUES (6, 6, 'test5', 0, 1, '办公室', '2022-06-07 20:40:47', '2022-06-07 20:40:47');
INSERT INTO `t_department_staff` VALUES (7, 7, 'test6', 0, 1, '办公室', '2022-06-07 20:40:47', '2022-06-07 20:40:47');
INSERT INTO `t_department_staff` VALUES (8, 8, 'test7', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (9, 9, 'test8', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (10, 10, 'test9', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (11, 11, 'test10', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (12, 12, 'test11', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (13, 13, 'test12', 0, 2, '纪委办公室', '2022-06-07 20:42:03', '2022-06-07 20:42:03');
INSERT INTO `t_department_staff` VALUES (14, 14, 'test13', 0, 2, '纪委办公室', '2022-06-07 20:42:04', '2022-06-07 20:42:04');
INSERT INTO `t_department_staff` VALUES (15, 15, 'test14', 1, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (16, 16, 'test15', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (17, 17, 'test16', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (18, 18, 'test17', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (19, 19, 'test18', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (20, 20, 'test19', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (21, 21, 'test20', 0, 3, '人事处', '2022-06-07 20:43:14', '2022-06-07 20:43:14');
INSERT INTO `t_department_staff` VALUES (22, 22, 'test21', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (23, 23, 'test22', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (24, 24, 'test23', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (25, 25, 'test24', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (26, 26, 'test25', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (27, 27, 'test26', 0, 4, '教务处', '2022-06-07 20:49:08', '2022-06-07 20:49:08');
INSERT INTO `t_department_staff` VALUES (28, 28, 'test27', 1, 5, '学生处', '2022-06-07 20:50:05', '2022-06-07 20:50:05');
INSERT INTO `t_department_staff` VALUES (29, 29, 'test28', 0, 5, '学生处', '2022-06-07 20:50:05', '2022-06-07 20:50:05');
INSERT INTO `t_department_staff` VALUES (30, 30, 'test29', 0, 5, '学生处', '2022-06-07 20:50:05', '2022-06-07 20:50:05');
INSERT INTO `t_department_staff` VALUES (32, 32, 'pl', 0, 5, '学生处', '2022-06-07 20:28:57', '2022-06-07 20:28:57');
INSERT INTO `t_department_staff` VALUES (33, 33, 'yyds', 0, 5, '学生处', '2022-06-07 20:31:01', '2022-06-07 20:31:01');
INSERT INTO `t_department_staff` VALUES (34, 34, 'whisper', 0, 5, '学生处', '2022-06-08 19:02:24', '2022-06-08 19:02:24');
INSERT INTO `t_department_staff` VALUES (35, 35, 'Riou', 1, 1, '办公室', '2022-06-09 10:45:21', '2022-06-09 10:45:23');
INSERT INTO `t_department_staff` VALUES (36, 31, 'Pymjl', 1, 6, '教务处', '2022-06-09 12:02:14', '2022-06-09 12:02:18');

-- ----------------------------
-- Table structure for t_faculty
-- ----------------------------
DROP TABLE IF EXISTS `t_faculty`;
CREATE TABLE `t_faculty`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '系id，主键，自增',
  `faculty_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '系名',
  `faculty_head` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系主任姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_faculty
-- ----------------------------

-- ----------------------------
-- Table structure for t_faculty_details
-- ----------------------------
DROP TABLE IF EXISTS `t_faculty_details`;
CREATE TABLE `t_faculty_details`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `faculty_id` bigint(0) NOT NULL COMMENT '系id',
  `department_id` int(0) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_faculty_details
-- ----------------------------
INSERT INTO `t_faculty_details` VALUES (1, 1, 1);
INSERT INTO `t_faculty_details` VALUES (2, 1, 2);
INSERT INTO `t_faculty_details` VALUES (3, 1, 3);
INSERT INTO `t_faculty_details` VALUES (4, 1, 4);
INSERT INTO `t_faculty_details` VALUES (5, 1, 5);
INSERT INTO `t_faculty_details` VALUES (13, 1, 6);
INSERT INTO `t_faculty_details` VALUES (14, 1, 10);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id.主键自增',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名，邮箱账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `gender` int(0) NOT NULL COMMENT '性别，0为女，1为男',
  `age` int(0) NOT NULL COMMENT '年龄',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '银行账号',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  `identity` int(0) NOT NULL DEFAULT 0 COMMENT '身份，0为普通用户，1为管理员，-1为封禁用户',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `'unique_username'`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'example0@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test0', 1, 0, '1231231321321312312', '11111111111', '2022-06-07 20:19:59', '2022-06-07 20:19:59', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (2, 'example1@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test1', 1, 1, '1231231321321312312', '11111111111', '2022-06-07 20:19:59', '2022-06-07 20:19:59', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (3, 'example2@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test2', 1, 2, '1231231321321312312', '11111111111', '2022-06-07 20:19:59', '2022-06-07 20:19:59', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (4, 'example3@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test3', 1, 3, '1231231321321312312', '11111111111', '2022-06-07 20:19:59', '2022-06-07 20:19:59', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (5, 'example4@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test4', 1, 4, '1231231321321312312', '11111111111', '2022-06-07 20:19:59', '2022-06-07 20:19:59', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (6, 'example5@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test5', 1, 5, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (7, 'example6@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test6', 1, 6, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (8, 'example7@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test7', 1, 7, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (10, 'example9@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test9', 1, 9, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (11, 'example10@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test10', 1, 10, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (12, 'example11@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test11', 1, 11, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (13, 'example12@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test12', 1, 12, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (14, 'example13@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test13', 1, 13, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (15, 'example14@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test14', 1, 14, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (16, 'example15@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test15', 1, 15, '1231231321321312312', '11111111111', '2022-06-07 20:20:00', '2022-06-07 20:20:00', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (17, 'example16@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test16', 1, 16, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (18, 'example17@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test17', 1, 17, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (19, 'example18@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test18', 1, 18, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (20, 'example19@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test19', 1, 19, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (21, 'example20@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test20', 1, 20, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (22, 'example21@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test21', 1, 21, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (23, 'example22@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test22', 1, 22, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (24, 'example23@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test23', 1, 23, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (25, 'example24@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test24', 1, 24, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (26, 'example25@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test25', 1, 25, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (27, 'example26@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test26', 1, 26, '1231231321321312312', '11111111111', '2022-06-07 20:20:01', '2022-06-07 20:20:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (28, 'example27@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test27', 1, 27, '1231231321321312312', '11111111111', '2022-06-07 20:20:02', '2022-06-07 20:20:02', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (29, 'example28@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test28', 1, 28, '1231231321321312312', '11111111111', '2022-06-07 20:20:02', '2022-06-07 20:20:02', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (30, 'example29@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'test29', 1, 29, '1231231321321312312', '11111111111', '2022-06-07 20:20:02', '2022-06-07 20:20:02', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (31, 'pymjl@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'Pymjl', 1, 20, '1111111111111111', '19136310161', '2022-06-07 20:25:48', '2022-06-09 12:00:09', 1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (32, '1792243149@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'pl', 1, 20, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '18151584912', '2022-06-07 20:28:57', '2022-06-07 20:28:57', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (33, '2865218924@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'yyds', 1, 20, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '18151584978', '2022-06-07 20:31:01', '2022-06-07 20:31:01', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (34, '2522105859@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'whisper', 0, 20, '1111111111111', '19136311111', '2022-06-08 19:02:24', '2022-06-08 19:02:24', 1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (35, '1982027616@qq.com', '40f45ab28ca67a9557489bb5a861ad88664c8b104e8461089c893e183716f020', 'Riou', 0, 18, '1234567890', '10086', '2022-06-08 20:24:05', '2022-06-09 02:55:41', 1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');

-- ----------------------------
-- Table structure for t_wage_details
-- ----------------------------
DROP TABLE IF EXISTS `t_wage_details`;
CREATE TABLE `t_wage_details`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(0) NOT NULL COMMENT '职工id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职工姓名',
  `basic_wage` int(0) NOT NULL COMMENT '基本工资',
  `bonus` int(0) NOT NULL COMMENT '奖金',
  `fine` int(0) NOT NULL COMMENT '罚款',
  `tax` int(0) NOT NULL COMMENT '税收',
  `sum_wage` int(0) NOT NULL COMMENT '总工资',
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '银行卡号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(0) NOT NULL COMMENT '发放状态 1已发放 2未发放',
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wage_details
-- ----------------------------
INSERT INTO `t_wage_details` VALUES (1, 1, 'test0', 9000, 300, 0, 279, 9021, '1231231321321312312', '2022-06-08 22:33:22', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (2, 2, 'test1', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:22', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (3, 3, 'test2', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (4, 4, 'test3', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (5, 5, 'test4', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (6, 6, 'test5', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (7, 7, 'test6', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '办公室');
INSERT INTO `t_wage_details` VALUES (8, 8, 'test7', 9000, 300, 0, 279, 9021, '1231231321321312312', '2022-06-08 22:33:23', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (9, 9, 'test8', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (10, 10, 'test9', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:23', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (11, 11, 'test10', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (12, 12, 'test11', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (13, 13, 'test12', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (14, 14, 'test13', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '纪委办公室');
INSERT INTO `t_wage_details` VALUES (15, 15, 'test14', 9000, 300, 0, 279, 9021, '1231231321321312312', '2022-06-08 22:33:24', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (16, 16, 'test15', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (17, 17, 'test16', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (18, 18, 'test17', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:24', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (19, 19, 'test18', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (20, 20, 'test9', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (21, 21, 'test20', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '人事处');
INSERT INTO `t_wage_details` VALUES (22, 22, 'test21', 9000, 300, 0, 279, 9021, '1231231321321312312', '2022-06-08 22:33:25', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (23, 23, 'test22', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (24, 24, 'test23', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (25, 25, 'test24', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (26, 26, 'test25', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:25', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (27, 27, 'test26', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:26', 1, '教务处');
INSERT INTO `t_wage_details` VALUES (28, 28, 'test27', 9000, 300, 0, 279, 9021, '1231231321321312312', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (29, 29, 'test28', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (30, 30, 'test29', 6000, 300, 0, 189, 6111, '1231231321321312312', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (31, 31, 'Pymjl', 6000, 300, 0, 189, 6111, '1111111111111111', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (32, 32, 'pl', 6000, 300, 0, 189, 6111, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (33, 33, 'yyds', 6000, 0, 200, 174, 5626, 'xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx', '2022-06-08 22:33:26', 1, '学生处');
INSERT INTO `t_wage_details` VALUES (34, 34, 'whisper', 6000, 0, 50, 179, 5771, '1111111111111', '2022-06-08 22:33:26', 1, '学生处');

SET FOREIGN_KEY_CHECKS = 1;
