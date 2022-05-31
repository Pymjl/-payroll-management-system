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

 Date: 31/05/2022 21:45:21
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
  `working_hours` int(0) NOT NULL COMMENT '工作时长',
  `status` int(0) NOT NULL COMMENT '工作状态（-1早退、0缺勤、1正常）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间（签到时间）',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间（签退时间）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_attendance_personal
-- ----------------------------

-- ----------------------------
-- Table structure for t_basic_wage
-- ----------------------------
DROP TABLE IF EXISTS `t_basic_wage`;
CREATE TABLE `t_basic_wage`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '职位 1普通员工 2部门主管',
  `basic_wage` int(0) NOT NULL COMMENT '基本工资',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_basic_wage
-- ----------------------------
INSERT INTO `t_basic_wage` VALUES (1, 5000);
INSERT INTO `t_basic_wage` VALUES (2, 8000);

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `department_id` int(0) NOT NULL COMMENT '部门的编号',
  `department_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门的名字',
  `department_num` int(0) NULL DEFAULT NULL COMMENT '部门员工总数',
  `department_boss_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '部门老大的名字',
  `department_boss_id` int(0) NULL DEFAULT NULL COMMENT '部门老大的编号',
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '安保', 2, '小明', 1);
INSERT INTO `t_department` VALUES (2, '保洁', 2, '小红', 2);
INSERT INTO `t_department` VALUES (3, '教书', NULL, '老师', NULL);
INSERT INTO `t_department` VALUES (4, '教书4', NULL, '老师4', NULL);

-- ----------------------------
-- Table structure for t_department_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_department_staff`;
CREATE TABLE `t_department_staff`  (
  `staff_id` int(0) NOT NULL COMMENT '员工的编号',
  `staff_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工的名字',
  `staff_position` int(0) NULL DEFAULT NULL COMMENT '员工的职位',
  `staff_department_id` int(0) NULL DEFAULT NULL COMMENT '员工所属部门的编号',
  `staff_leave_num` int(0) NULL DEFAULT NULL COMMENT '员工一个月早退的次数',
  `staff_late_num` int(0) NULL DEFAULT NULL COMMENT '员工一个月迟到的次数',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department_staff
-- ----------------------------
INSERT INTO `t_department_staff` VALUES (1, '小明', 1, 1, 6, 5);
INSERT INTO `t_department_staff` VALUES (2, '小军', 0, 1, 6, 5);

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
  `department_id` bigint(0) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_faculty_details
-- ----------------------------

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff`  (
  `staff_id` int(0) NOT NULL COMMENT '员工编号',
  `staff_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工名字',
  `staff_sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工性别',
  `staff_age` int(0) NULL DEFAULT NULL COMMENT '员工年龄',
  `staff_department_id` int(0) NULL DEFAULT NULL COMMENT '员工所属部门编号',
  `staff_phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '员工电话',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES (1, '小明', '男', 23, 1, '17171717');
INSERT INTO `t_staff` VALUES (2, '小红', '女', 22, 1, '14141414');
INSERT INTO `t_staff` VALUES (3, '小谢', '女', 21, 1, '12121212');
INSERT INTO `t_staff` VALUES (4, '小杨', '男', 42, 2, '13131313');

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
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'pymjl@qq.com', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'Pymjl', 1, 20, 'laborum eu', '19136310161', '2022-05-27 11:56:25', '2022-05-31 21:12:06', 1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (2, '1792243149@qq.com', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test0', 1, 0, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', -1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (3, 'test1', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test1', 1, 1, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (4, 'test2', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test2', 1, 2, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (5, 'test3', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test3', 1, 3, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (6, 'test4', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test4', 1, 4, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (7, 'test5', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test5', 1, 5, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (8, 'test6', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test6', 1, 6, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (9, 'test7', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test7', 1, 7, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (10, 'test8', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test8', 1, 8, '1231231321321312312', '11111111111', '2022-05-27 16:24:40', '2022-05-27 16:24:40', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (11, 'test9', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test9', 1, 9, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (12, 'test10', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test10', 1, 10, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (13, 'test11', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test11', 1, 11, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', -1, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (14, 'test12', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test12', 1, 12, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (15, 'test13', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test13', 1, 13, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (16, 'test14', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test14', 1, 14, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (17, 'test15', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test15', 1, 15, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (18, 'test16', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test16', 1, 16, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (19, 'test17', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test17', 1, 17, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (20, 'test18', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test18', 1, 18, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (21, 'test19', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test19', 1, 19, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (22, 'test20', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test20', 1, 20, '1231231321321312312', '11111111111', '2022-05-27 16:24:41', '2022-05-27 16:24:41', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (23, 'test21', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test21', 1, 21, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (24, 'test22', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test22', 1, 22, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (25, 'test23', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test23', 1, 23, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (26, 'test24', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test24', 1, 24, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (27, 'test25', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test25', 1, 25, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (28, 'test26', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test26', 1, 26, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (29, 'test27', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test27', 1, 27, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (30, 'test28', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test28', 1, 28, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (31, 'test29', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'test29', 1, 29, '1231231321321312312', '11111111111', '2022-05-27 16:24:42', '2022-05-27 16:24:42', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');
INSERT INTO `t_user` VALUES (32, '1982027616@qq.com', '9ee416167bdcc1bb0443b636d5b9bb239e00e2a20e99ce069258c9d1f4f64628', 'Riou', 0, 18, '1234567890', '1008611', '2022-05-28 16:06:25', '2022-05-28 16:06:25', 0, 'https://pymjl.oss-cn-shanghai.aliyuncs.com/picgo/default.jpg');

-- ----------------------------
-- Table structure for t_wage_details
-- ----------------------------
DROP TABLE IF EXISTS `t_wage_details`;
CREATE TABLE `t_wage_details`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `employee_id` int(0) NOT NULL COMMENT '职工id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '职工姓名',
  `basic_wage` int(0) NOT NULL COMMENT '基本工资',
  `bonus` int(0) NOT NULL COMMENT '奖金',
  `fine` int(0) NOT NULL COMMENT '罚款',
  `tax` int(0) NOT NULL COMMENT '税收',
  `sum_wage` int(0) NOT NULL COMMENT '总工资',
  `account_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '银行卡号',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `status` int(0) NOT NULL COMMENT '发放状态 1已发放 2未发放',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wage_details
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
