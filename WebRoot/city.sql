/*
Navicat MySQL Data Transfer

Source Server         : springboot
Source Server Version : 50022
Source Host           : 127.0.0.1:3306
Source Database       : springbootdemo

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2019-05-21 20:26:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL auto_increment COMMENT '城市ID',
  `province_id` varchar(20) default NULL COMMENT '省份ID',
  `city_name` varchar(100) default NULL COMMENT '城市名称',
  `description` varchar(255) default NULL COMMENT ' 城市描述',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city
-- ----------------------------
BEGIN;
INSERT INTO `city` (`id`, `province_id`, `city_name`, `description`) VALUES ('1', '1203', '常熟市', '嗯哼，我也不知道鬼'), ('2', '0603', 'xx', '噢，原来是这个样子的'), ('3', '1203', '兰溪市', '啊哈，我发现了什么玩意？？？？'), ('5', '1205', 'wanlin', '啊哈，我发现了什么玩意？？？？'), ('8', '1205', 'wanlin', '啊哈，我发现了什么玩意？？？？'), ('9', '1205', 'wanlin1', '啊哈，我发现了什么玩意？？？？'), ('10', '1205', 'wanlin', '啊哈，我发现了什么玩意？？？？'), ('11', '1205', 'wanlin', '啊哈，我发现了什么玩意？？？？'), ('13', '1205', 'lin', '啊哈，........................');
COMMIT;

-- ----------------------------
-- Table structure for user_t
-- ----------------------------
DROP TABLE IF EXISTS `user_t`;
CREATE TABLE `user_t` (
  `id` int(11) NOT NULL auto_increment,
  `user_name` varchar(40) NOT NULL,
  `password` varchar(255) NOT NULL,
  `age` int(4) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_t
-- ----------------------------
BEGIN;
INSERT INTO `user_t` (`id`, `user_name`, `password`, `age`) VALUES ('1', '测试', 'sfasgfaf', '24');
COMMIT;
