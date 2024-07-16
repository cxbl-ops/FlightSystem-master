/*
 Navicat Premium Dump SQL

 Source Server         : tx
 Source Server Type    : MySQL
 Source Server Version : 80022 (8.0.22-cynos)
 Source Host           : sh-cynosdbmysql-grp-80ojo25y.sql.tencentcdb.com:22360
 Source Schema         : flight_system

 Target Server Type    : MySQL
 Target Server Version : 80022 (8.0.22-cynos)
 File Encoding         : 65001

 Date: 08/07/2024 16:41:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_air
-- ----------------------------
DROP TABLE IF EXISTS `tb_air`;
CREATE TABLE `tb_air`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `airName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_air
-- ----------------------------
INSERT INTO `tb_air` VALUES (1, '861 Silver St, Newnham');
INSERT INTO `tb_air` VALUES (2, '907 FuXingMenNei Street, XiCheng District');
INSERT INTO `tb_air` VALUES (3, '890 028 County Rd, Yanqing District');
INSERT INTO `tb_air` VALUES (4, '148 Shennan Ave, Futian District');
INSERT INTO `tb_air` VALUES (5, '649 Lodge Ln, Toxteth');
INSERT INTO `tb_air` VALUES (6, '171 Shanhu Rd');
INSERT INTO `tb_air` VALUES (7, '157 Shennan Ave, Futian District');
INSERT INTO `tb_air` VALUES (8, '450 Tremont Road');
INSERT INTO `tb_air` VALUES (9, '289 East Wangfujing Street, Dongcheng District ');
INSERT INTO `tb_air` VALUES (10, '1-1-17 Deshiro, Nishinari Ward');
INSERT INTO `tb_air` VALUES (11, '937 Pedway');
INSERT INTO `tb_air` VALUES (12, '954 Hongqiao Rd., Xu Hui District');
INSERT INTO `tb_air` VALUES (13, '1-5-17, Higashi-Shimbashi, Minato-ku');
INSERT INTO `tb_air` VALUES (14, '604 Tangyuan Street 5th Alley, Airport Road, Baiyun');
INSERT INTO `tb_air` VALUES (15, '13-3-17 Toyohira 3 Jo, Toyohira Ward');

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `cityName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `countryId` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `id`(`countryId` ASC) USING BTREE,
  CONSTRAINT `id` FOREIGN KEY (`countryId`) REFERENCES `tb_country` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_city
-- ----------------------------
INSERT INTO `tb_city` VALUES (1, 'Canberra', 3);
INSERT INTO `tb_city` VALUES (2, 'Leicester', 4);
INSERT INTO `tb_city` VALUES (3, 'Paramaribo', 6);
INSERT INTO `tb_city` VALUES (4, 'Podgorica', 1);
INSERT INTO `tb_city` VALUES (5, 'Vilnius', 7);
INSERT INTO `tb_city` VALUES (6, 'Abuja', 19);
INSERT INTO `tb_city` VALUES (7, 'Dushanbe', 16);
INSERT INTO `tb_city` VALUES (8, 'Leicester', 5);
INSERT INTO `tb_city` VALUES (9, 'Yerevan', 10);
INSERT INTO `tb_city` VALUES (10, 'Ouagadougou', 2);
INSERT INTO `tb_city` VALUES (11, 'Honiara', 15);
INSERT INTO `tb_city` VALUES (12, 'Yaoundé', 8);
INSERT INTO `tb_city` VALUES (13, 'Yamoussoukro', 14);
INSERT INTO `tb_city` VALUES (14, 'Funafuti', 9);
INSERT INTO `tb_city` VALUES (15, 'New Delhi', 18);

-- ----------------------------
-- Table structure for tb_country
-- ----------------------------
DROP TABLE IF EXISTS `tb_country`;
CREATE TABLE `tb_country`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `countryName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `countryName`(`countryName` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1038 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_country
-- ----------------------------
INSERT INTO `tb_country` VALUES (6, 'Angola');
INSERT INTO `tb_country` VALUES (3, 'Benin');
INSERT INTO `tb_country` VALUES (14, 'Brunei Darussalam');
INSERT INTO `tb_country` VALUES (2, 'Ecuador');
INSERT INTO `tb_country` VALUES (16, 'Eswatini');
INSERT INTO `tb_country` VALUES (11, 'Kiribati');
INSERT INTO `tb_country` VALUES (18, 'Kuwait');
INSERT INTO `tb_country` VALUES (10, 'Lesotho');
INSERT INTO `tb_country` VALUES (8, 'Malawi');
INSERT INTO `tb_country` VALUES (7, 'Malaysia');
INSERT INTO `tb_country` VALUES (15, 'Mali');
INSERT INTO `tb_country` VALUES (5, 'Mauritius');
INSERT INTO `tb_country` VALUES (13, 'Myanmar');
INSERT INTO `tb_country` VALUES (20, 'Namibia');
INSERT INTO `tb_country` VALUES (1, 'Netherlands');
INSERT INTO `tb_country` VALUES (12, 'Qatar');
INSERT INTO `tb_country` VALUES (17, 'Samoa');
INSERT INTO `tb_country` VALUES (19, 'Samoa');
INSERT INTO `tb_country` VALUES (4, 'South Africa');
INSERT INTO `tb_country` VALUES (9, 'United Republic of Tanzania');

-- ----------------------------
-- Table structure for tb_flight
-- ----------------------------
DROP TABLE IF EXISTS `tb_flight`;
CREATE TABLE `tb_flight`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `flightId` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `planTime` datetime NOT NULL,
  `estimatedTime` datetime NOT NULL,
  `actualTime` datetime NOT NULL,
  `actualArrived` datetime NOT NULL,
  `departure` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `destination` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `delay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `isCompleted` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1083 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_flight
-- ----------------------------
INSERT INTO `tb_flight` VALUES (1075, 'MK001', '2024-05-27 04:54:43', '2024-05-27 04:54:45', '2024-05-27 04:54:46', '2024-05-27 04:54:48', 'Brunei Darussalam-Yamoussoukro-1-5-17, Higashi-Shimbashi, Minato-ku', 'Ecuador-Ouagadougou-1-1-17 Deshiro, Nishinari Ward', '是', '在途中');
INSERT INTO `tb_flight` VALUES (1076, 'MK002', '2024-05-30 11:58:37', '2024-05-30 11:58:38', '2024-05-30 11:58:40', '2024-05-30 11:58:42', 'Brunei Darussalam-Yamoussoukro-1-5-17, Higashi-Shimbashi, Minato-ku', 'Angola-Paramaribo-890 028 County Rd, Yanqing District', '是', '已完成');
INSERT INTO `tb_flight` VALUES (1077, 'MK003', '2024-05-30 02:10:27', '2024-05-30 02:10:28', '2024-05-30 02:10:30', '2024-05-30 02:10:32', 'Malaysia-Vilnius-649 Lodge Ln, Toxteth', 'Angola-Paramaribo-890 028 County Rd, Yanqing District', '是', '在途中');
INSERT INTO `tb_flight` VALUES (1078, 'MK004', '2024-05-30 05:19:15', '2024-05-30 05:19:16', '2024-05-30 05:19:18', '2024-05-30 05:19:20', 'Brunei Darussalam-Yamoussoukro-1-5-17, Higashi-Shimbashi, Minato-ku', 'Ecuador-Ouagadougou-1-1-17 Deshiro, Nishinari Ward', '是', '未开始');
INSERT INTO `tb_flight` VALUES (1080, 'MK007', '2024-05-30 05:26:22', '2024-05-30 05:26:24', '2024-05-30 05:26:26', '2024-05-30 05:26:27', 'Mali-Honiara-937 Pedway', 'Ecuador-Ouagadougou-1-1-17 Deshiro, Nishinari Ward', '是', '在途中');
INSERT INTO `tb_flight` VALUES (1082, 'Mk009', '2024-06-07 10:45:05', '2024-06-07 10:45:07', '2024-06-07 10:45:18', '2024-06-07 10:45:28', 'Angola-Paramaribo-890 028 County Rd, Yanqing District', 'Angola-Paramaribo-890 028 County Rd, Yanqing District', '是', '已完成');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (104, 'xiaoweichen701@qp.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd145', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=');
INSERT INTO `tb_user` VALUES (103, 'xiaoweichen701@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=');
INSERT INTO `tb_user` VALUES (102, '456789794@qq.com', '0xb5527d6454d2b4e2ebf0505ce35942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (101, '123@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (100, '123@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (99, '123456@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (98, '123456@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (97, '1336566721@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (96, '1336566721@qq.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (95, '1254181605@qq.com', '0x1cae7f57038fe17852e6b7c27ae24ad208715a66', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (94, '1254181605@qq.com', '0x1cae7f57038fe17852e6b7c27ae24ad208715a66', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=');
INSERT INTO `tb_user` VALUES (93, 'xiaoweichen701@gmail.com', '0xb5527d6454d2b4e2ebf0505ce3e942cd48dbd143', '73l8gRjwLftklgfdXT+MdiMEjJwGPVMsyVxe16iYpk8=');

SET FOREIGN_KEY_CHECKS = 1;
