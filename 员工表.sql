/*
 Navicat Premium Data Transfer

 Source Server         : mysql_root
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 27/09/2021 19:00:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `salary` double(12, 2) DEFAULT NULL,
  `dizhi` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `job` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`eid`) USING BTREE,
  INDEX `fk_pid`(`pid`) USING BTREE,
  CONSTRAINT `fk_pid` FOREIGN KEY (`pid`) REFERENCES `department` (`pid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (2, '夏河', '男', 702, 5601.00, '湖南省', '开发工程师', '163263704755413020627.png');
INSERT INTO `employee` VALUES (6, '夏', '男', 701, 8000.00, '湖南省', '开发工程师', '163263650654519193320.png');
INSERT INTO `employee` VALUES (9, '胡梓卓', '女', 701, 9298.00, '湖北省', '项目主管', '163262463950778917261.png');
INSERT INTO `employee` VALUES (12, '苏服务', '男', 802, 2501.00, '湖北省', '产品经理', '163262536505865631734.png');
INSERT INTO `employee` VALUES (13, '水电费', '男', 701, 12321.00, '湖北省', '开发工程师', '163265170152752265025.png');
INSERT INTO `employee` VALUES (14, '分割稿费', '女', 702, 1232.00, '湖北省', '开发工程师', '163265172106195499846.png');
INSERT INTO `employee` VALUES (15, '海豚有海', '女', 802, 2334.00, '广东省', '开发工程师', '163265178438917383785.png');
INSERT INTO `employee` VALUES (16, '环绕', '女', 802, 8000.00, '湖北省', '产品经理', '163265179420379437078.png');
INSERT INTO `employee` VALUES (17, '还有人', '男', 702, 2344.00, '广东省', '开发工程师', '16326513698345951898.png');
INSERT INTO `employee` VALUES (18, '教育具有', '女', 702, 33344.00, '云南省', '项目主管', '163265173248513632147.png');
INSERT INTO `employee` VALUES (19, '肥嘟嘟', '男', 702, 2334.00, '湖南省', '产品经理', '163265130240294761091.png');
INSERT INTO `employee` VALUES (20, '而恶搞', '男', 702, 5600.00, '广东省', '开发工程师', '163265174216842556872.png');
INSERT INTO `employee` VALUES (21, '我问他', '女', 702, 4421.00, '湖北省', '产品经理', '163265139639523564495.png');
INSERT INTO `employee` VALUES (22, '分割2', '女', 702, 2333.00, '广东省', '开发工程师', '163265175187134967183.png');
INSERT INTO `employee` VALUES (23, '天通苑', '女', 702, 8000.00, '广东省', '开发工程师', '163265123007196984570.png');
INSERT INTO `employee` VALUES (24, '打分法', '女', 802, 2231.00, '湖南省', '产品经理', '163265138119161664874.png');
INSERT INTO `employee` VALUES (25, '刚好', '女', 702, 8000.00, '广东省', '产品经理', '163265176084550399571.png');
INSERT INTO `employee` VALUES (26, '发发发', '女', 701, 2233.00, '广东省', '产品经理', '163265096689718406025.png');
INSERT INTO `employee` VALUES (27, '565', '女', 702, 12000.00, '广东省', '开发工程师', '163265135542972422212.png');
INSERT INTO `employee` VALUES (28, 'wfwer', '男', 702, 1200.00, '海南省', '产品经理', '163265177241787093387.png');
INSERT INTO `employee` VALUES (29, '刚好', '男', 701, 6500.00, '云南省', '产品经理', '163265171160724310155.png');
INSERT INTO `employee` VALUES (30, '哭一集', '女', 702, 5600.00, '湖南省', '会计', '163265666543632591014.png');
INSERT INTO `employee` VALUES (31, '空压机', '男', 803, 6500.00, '云南省', '运维工程师', '163265669707598943601.png');
INSERT INTO `employee` VALUES (32, '预警', '男', 802, 2500.00, '广东省', '会计', '163265667558693938347.png');
INSERT INTO `employee` VALUES (33, '土壤', '女', 802, 6900.00, '湖南省', '会计', '163265668638764298848.png');
INSERT INTO `employee` VALUES (34, '合同聚一块', '女', 702, 2600.00, '湖南省', '项目主管', '16326569088611145287.png');
INSERT INTO `employee` VALUES (35, '功能', '女', 702, 3456.00, '湖南省', '会计', '163265691976752476451.png');
INSERT INTO `employee` VALUES (36, '提货人', '女', 803, 8000.00, '湖南省', '项目主管', '163265705608537567981.png');
INSERT INTO `employee` VALUES (37, '预热图', '女', 702, 23534.00, '广东省', '项目主管', '163265704365587788343.png');
INSERT INTO `employee` VALUES (38, '规划条件和他', '女', 701, 328.00, '广东省', '产品经理', '163265716353860057620.png');
INSERT INTO `employee` VALUES (39, '焚膏继晷黄金国际', '男', 701, 5600.00, '广东省', '实施工程师', '16326579930073465763.png');

SET FOREIGN_KEY_CHECKS = 1;
