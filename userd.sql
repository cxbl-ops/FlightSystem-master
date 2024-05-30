/*
 Navicat Premium Data Transfer

 Source Server         : tx
 Source Server Type    : MySQL
 Source Server Version : 80022 (8.0.22-cynos)
 Source Host           : sh-cynosdbmysql-grp-80ojo25y.sql.tencentcdb.com:22360
 Source Schema         : userd

 Target Server Type    : MySQL
 Target Server Version : 80022 (8.0.22-cynos)
 File Encoding         : 65001

 Date: 10/05/2024 16:27:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_air
-- ----------------------------
DROP TABLE IF EXISTS `tb_air`;
CREATE TABLE `tb_air` (
  `id` int NOT NULL AUTO_INCREMENT,
  `airName` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_air
-- ----------------------------
BEGIN;
INSERT INTO `tb_air` (`id`, `airName`) VALUES (1, '洛杉矶机场');
INSERT INTO `tb_air` (`id`, `airName`) VALUES (2, '上海虹桥机场');
COMMIT;

-- ----------------------------
-- Table structure for tb_city
-- ----------------------------
DROP TABLE IF EXISTS `tb_city`;
CREATE TABLE `tb_city` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cityName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `countryId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id` (`countryId`),
  CONSTRAINT `id` FOREIGN KEY (`countryId`) REFERENCES `tb_country` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_city
-- ----------------------------
BEGIN;
INSERT INTO `tb_city` (`id`, `cityName`, `countryId`) VALUES (1, '洛杉矶', 1);
INSERT INTO `tb_city` (`id`, `cityName`, `countryId`) VALUES (2, '上海', 2);
COMMIT;

-- ----------------------------
-- Table structure for tb_country
-- ----------------------------
DROP TABLE IF EXISTS `tb_country`;
CREATE TABLE `tb_country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `countryName` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `countryName` (`countryName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_country
-- ----------------------------
BEGIN;
INSERT INTO `tb_country` (`id`, `countryName`) VALUES (2, '中国');
INSERT INTO `tb_country` (`id`, `countryName`) VALUES (1, '美国');
COMMIT;

-- ----------------------------
-- Table structure for tb_flight
-- ----------------------------
DROP TABLE IF EXISTS `tb_flight`;
CREATE TABLE `tb_flight` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flightId` varchar(255) NOT NULL,
  `planTime` date NOT NULL,
  `estimatedTime` date NOT NULL,
  `actualTime` date NOT NULL,
  `actualArrived` date NOT NULL,
  `departure` varchar(255) NOT NULL DEFAULT '',
  `destination` varchar(255) NOT NULL DEFAULT '',
  `delay` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_flight
-- ----------------------------
BEGIN;
INSERT INTO `tb_flight` (`id`, `flightId`, `planTime`, `estimatedTime`, `actualTime`, `actualArrived`, `departure`, `destination`, `delay`) VALUES (12, 'MC001', '2024-05-10', '2024-05-10', '2024-05-10', '2024-05-10', '中国-上海-上海虹桥机场', '美国-洛杉矶-洛杉矶机场', '是');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `username` varchar(255) NOT NULL DEFAULT '',
  `passwd` varchar(255) NOT NULL DEFAULT '',
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` (`username`, `passwd`, `id`) VALUES ('3014806575@qq.com', '123456', 1);
INSERT INTO `tb_user` (`username`, `passwd`, `id`) VALUES ('xiaoweichen701@gmail.com', '123456', 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
