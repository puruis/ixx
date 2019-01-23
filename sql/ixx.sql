/*
Navicat MySQL Data Transfer

Source Server         : localDB
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ixx

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-01-23 12:40:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l_dict
-- ----------------------------
DROP TABLE IF EXISTS `l_dict`;
CREATE TABLE `l_dict` (
  `id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` int(10) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) COLLATE utf8_bin DEFAULT '0' COMMENT '父级编号',
  `create_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` int(2) DEFAULT '0' COMMENT '0:正常 1：删除',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`name`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of l_dict
-- ----------------------------
INSERT INTO `l_dict` VALUES ('0384c3e892b647d0aec9745dfc084f0a', '禁用', 'DELETE', 'userStatus', '用户状态', '2', '0', 'admin', '2019-01-05 20:49:50', null, null, '用户禁用状态', '0');
INSERT INTO `l_dict` VALUES ('040e4eddcff0437eb51649c479fbd439', '菜单', '1', 'menuType', '菜单类型', '-88', '0', 'admin', '2018-12-03 17:47:45', null, null, '菜单类型', '0');
INSERT INTO `l_dict` VALUES ('1b873bc69e9b4a0e90f63b06f5122599', '禁用', '1', 'roleType', '角色状态', '2', '0', 'admin', '2018-12-05 18:33:38', null, null, '角色状态', '0');
INSERT INTO `l_dict` VALUES ('2490523ad0d548bb89bbcc99bc558065', '正常', 'NORMAL', 'userStatus', '用户状态', '1', '0', 'admin', '2019-01-05 20:49:17', null, null, '用户正常状态', '0');
INSERT INTO `l_dict` VALUES ('383e069417454da4b8dc215158adee7a', '目录', '0', 'menuType', '菜单类型', '-99', '0', 'admin', '2018-12-03 17:46:52', 'admin', '2018-12-03 17:47:52', '菜单类型', '0');
INSERT INTO `l_dict` VALUES ('3e32c0551a074c69b4abeead58b98506', 'Windows', 'Windows', 'OS', '操作系统', '1', '0', 'admin', '2019-01-05 15:09:43', null, null, '微软', '0');
INSERT INTO `l_dict` VALUES ('6d3cac55314e4317815d8ed92a4acd58', '正常', '0', 'roleType', '角色状态', '1', '0', 'admin', '2018-12-05 18:33:17', null, null, '角色状态', '0');
INSERT INTO `l_dict` VALUES ('84f8f792949148f9937641f19a20cd03', 'Linux', '1', 'OS', '操作系统', '2', '0', 'admin', '2019-01-05 15:10:38', null, null, 'Linux', '0');
INSERT INTO `l_dict` VALUES ('a0dc932d6e734a86a2df2dcdc5fee944', '按钮', '2', 'menuType', '菜单类型', '-777', '0', 'admin', '2018-12-03 17:48:32', null, null, '菜单类型', '0');
INSERT INTO `l_dict` VALUES ('b23aa12f43d0484bb02ccef776e55ecc', 'c', 'c', 'c', 'c', '2', '0', 'admin', '2018-12-07 20:18:23', null, null, 'sd', '1');

-- ----------------------------
-- Table structure for l_file
-- ----------------------------
DROP TABLE IF EXISTS `l_file`;
CREATE TABLE `l_file` (
  `id` varchar(255) NOT NULL,
  `file_original_name` varchar(255) DEFAULT NULL COMMENT '原文件名',
  `file_name` varchar(500) DEFAULT NULL COMMENT '生成的文件名称',
  `file_type` varchar(50) DEFAULT NULL COMMENT '文件类型',
  `file_size` double(5,0) DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) DEFAULT NULL COMMENT '远端储存地址',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_file
-- ----------------------------

-- ----------------------------
-- Table structure for l_login_notes
-- ----------------------------
DROP TABLE IF EXISTS `l_login_notes`;
CREATE TABLE `l_login_notes` (
  `id` varchar(50) NOT NULL,
  `user_name` varchar(502) DEFAULT NULL COMMENT '用户名',
  `login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登陆时间',
  `logout_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注销时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip地址',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `operating_sys` varchar(500) DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
  `rsv1` varchar(255) DEFAULT NULL COMMENT '备注',
  `rsv2` varchar(255) DEFAULT NULL,
  `rsv3` varchar(255) DEFAULT NULL,
  `rsv4` varchar(255) DEFAULT NULL,
  `rsv5` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_login_notes
-- ----------------------------
INSERT INTO `l_login_notes` VALUES ('009dbb76eb454ccca131f83bbefc8458', 'admin', '2019-01-05 21:56:34', '2019-01-05 13:57:19', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('0239e05290824cc8bfd267281b9d6e17', 'admin', '2019-01-18 15:38:06', '2019-01-18 15:47:24', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('07b6d891ab0d4d2384abd1cc35f1f5da', 'admin', '2019-01-18 17:35:02', '2019-01-18 17:36:00', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('0b91638f651849a49edf0c368f6ff2a2', 'admin', '2019-01-05 20:13:52', '2019-01-05 12:17:46', '220.112.124.204', '中国-上海-上海-鹏博士', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('0c47fcff52aa4f7cab159512b0fa594a', 'admin', '2019-01-05 20:57:35', '2019-01-05 12:57:56', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('0ccbcaf704c44cf9833e05f5acc37cb1', 'admin', '2019-01-23 12:07:45', '2019-01-23 12:08:02', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('0ceea337c7d54365ae268cd2bf197d8b', 'admin', '2019-01-16 19:26:07', '2019-01-16 11:30:19', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('1132a49664884378bd017467aa66f16f', 'admin', '2019-01-18 17:39:45', '2019-01-18 17:40:01', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('1432db9be98b40dfb8f30a8cb9b77dc5', 'admin', '2019-01-23 09:59:06', '2019-01-23 09:59:46', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('1d5645e8da75453fb72e71be55648691', 'admin', '2019-01-08 22:49:13', '2019-01-11 04:08:07', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('1eb9e455ef574f109a9542a85d6a84c3', 'admin', '2019-01-18 17:33:30', '2019-01-18 17:34:00', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('2121cf4f15074cd681d7b1323942df42', 'admin', '2019-01-18 15:16:23', '2019-01-18 07:27:13', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('27d0bef83dfa4d2b8ca37d3d3d98fc07', 'admin', '2019-01-18 17:34:07', '2019-01-18 17:35:00', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('2ea2894a7a5b49849ef779b9c9e1bf95', 'admin', '2019-01-05 14:54:11', '2019-01-05 06:54:13', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('327bb7761f924e0e96e05527d58a5ac5', 'admin', '2019-01-05 23:46:02', '2019-01-05 15:46:41', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('3352cc9a205a4ffc8dc0be8dc6d79f8e', 'admin', '2019-01-18 17:48:48', '2019-01-18 17:50:25', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('389206ce79f04e6a9af841b23fb8cc94', 'admin', '2019-01-08 16:54:30', '2019-01-08 08:55:10', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('3918055097354a948d06cd31bbdf6f91', 'admin', '2019-01-08 21:03:36', '2019-01-08 13:04:13', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('39816677684340f6bbaf524fdf39b278', 'admin', '2019-01-03 20:14:41', '2019-01-03 12:15:07', '58.246.140.237', '中国上海上海联通', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('44f13d8a514d4a34b341be3d381220a2', 'admin', '2019-01-17 14:49:56', '2019-01-18 16:07:07', '192.168.1.119', 'XX-内网IP-XX-内网IP', 'OS X', 'Safari 11.1.2', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('487e76ef79264941ba8c74b4a6c506b0', 'admin', '2019-01-18 17:51:21', '2019-01-18 17:51:25', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('4ae77bf82bf94ba28b860d8f9eb26815', 'admin', '2019-01-05 19:07:57', '2019-01-05 11:13:50', '220.112.125.167', '中国-上海-上海-鹏博士', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('4d4a256542b4433ca663efb5706a6c64', 'admin', '2019-01-18 17:58:26', '2019-01-18 17:59:01', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('4ef74578d24b426281a2c6dc2fd948f2', 'admin', '2019-01-05 21:35:28', '2019-01-05 13:35:52', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('4f27f781f0f943698a3e9e38cd549bf7', 'admin', '2019-01-04 17:46:38', '2019-01-04 09:46:41', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 71.0.3578.98', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('538fd41b037b41068c380c12933fd98f', 'admin', '2019-01-18 15:27:53', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('56155e3be3284a89a4a1a428dbfec921', 'admin', '2019-01-18 17:42:04', '2019-01-18 17:42:22', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('5c3ea87183084698902403434c6adc46', 'admin', '2019-01-18 16:58:44', '2019-01-18 16:58:54', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('5de8f63a600e4901aeb232b8a767ef5f', 'admin', '2019-01-23 10:05:38', '2019-01-23 10:06:17', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('5fb4b7cd62f64c2b8b42254f6e157e01', 'admin', '2019-01-05 14:55:45', '2019-01-05 06:55:46', '220.112.124.204', '中国-上海-上海-鹏博士', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('6c7e23567887401589eeea1e2160d2b9', 'admin', '2019-01-06 12:04:42', '2019-01-06 04:12:33', '101.225.83.30', '中国-上海-上海-电信', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('6dd9453a99a04670aa8f564494328aa1', 'admin', '2019-01-05 19:14:31', '2019-01-05 11:14:51', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('6f85194c3cec4a9b9ceab8a3823d8157', 'admin', '2019-01-18 21:44:25', '2019-01-18 21:45:21', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('721d7f202ad84405b18838b2992d9363', 'admin', '2019-01-18 15:48:46', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('7de1dca09c3b42b9bf68794c82ad8b76', 'admin', '2019-01-12 12:27:56', '2019-01-12 04:35:16', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1.2', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('905acf6197c84816bfeb57b39230b233', 'admin', '2019-01-06 12:13:39', '2019-01-06 04:14:33', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('9d606e9434fb4161a727ca998aa0e4c4', 'admin', '2019-01-18 16:07:58', '2019-01-18 16:08:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('a164221cc100400580a3407e250cf375', 'admin', '2019-01-18 17:12:06', '2019-01-18 17:12:47', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('a382a1f3098046d4b989a4775a3cb079', 'admin', '2019-01-18 17:18:05', '2019-01-18 17:18:15', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('abf877d9d9a84b49a3b4c65a8ff9fc18', 'admin', '2019-01-18 16:41:37', '2019-01-18 16:42:24', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('ac294d9609264d3d9e760f22e7502b60', 'admin', '2019-01-23 01:14:39', '2019-01-23 01:15:28', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('acfa950a0ba9485e8a04cb9e9e2dd21b', 'admin', '2019-01-18 21:02:27', '2019-01-18 21:02:50', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('ad9fcbd392fc4581912f35228570164c', 'admin', '2019-01-05 15:43:45', '2019-01-05 07:43:47', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('af51c264037e4dd9b6ad8a2c1a8ee5e8', 'admin', '2019-01-18 17:23:34', '2019-01-18 17:23:40', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('b38333f0731a4bac87ba58e22b666dd2', 'admin', '2019-01-11 12:11:47', '2019-01-17 06:49:14', '192.168.1.119', 'XX-内网IP-XX-内网IP', 'OS X', 'Safari 11.1.2', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('b42787bf92fb4d5cb55288d1fabf3a3a', 'admin', '2019-01-18 15:52:43', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('b5d800b972b540c58b2c911d715c33fb', 'admin', '2019-01-17 22:46:16', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('b960b86a6a8d4b169a470e76ae67e5da', 'admin', '2019-01-14 15:47:01', '2019-01-17 06:49:15', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1.2', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('beaa16fe32954f408af81d916b3520f2', 'admin', '2019-01-05 21:00:54', '2019-01-05 13:01:34', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('c37039d3c75547f0b0c98f51add462f7', 'admin', '2019-01-14 15:33:45', '2019-01-17 06:49:16', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('ca83eb2c463b4ce1b289f718eba602ea', 'admin', '2019-01-05 21:51:45', '2019-01-05 13:52:14', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('cf435899d74f457bb7ed8c3db2c98a21', 'admin', '2019-01-05 22:05:52', '2019-01-05 14:06:31', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('d57fbc610c0445b5bc298b9251ee09aa', 'admin', '2019-01-05 15:20:20', '2019-01-05 07:20:22', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('d5ae85bab9a145fbb92bc0831d7a5877', 'admin', '2019-01-18 19:41:22', '2019-01-18 19:41:49', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('d60c5f015865476c8e2662df43793634', 'admin', '2019-01-05 21:12:16', '2019-01-05 13:12:39', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('d89d9237ab0845ea8cf35024eef0c4db', 'admin', '2019-01-14 17:05:07', '2019-01-17 06:49:17', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1.2', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('de5ff9940d5f47d3ab2ec285a582a10c', 'admin', '2019-01-17 16:51:19', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('e3813173729e41ee8acc231411f70f41', 'admin', '2019-01-18 21:48:09', '2019-01-18 21:48:17', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('e6398f59b8164a0bb5e10548fd34d853', 'admin', '2019-01-05 23:41:42', '2019-01-05 15:42:08', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('e72f0801e7db4499a10ffa28d5d7d3d3', 'admin', '2019-01-07 12:34:15', '2019-01-07 04:34:49', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('e892f8d1dcb34cd0b56e663a081b5762', 'admin', '2019-01-18 15:11:14', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('e8edd035da3a4b3f82f235b034e453f0', 'admin', '2019-01-05 20:20:05', '2019-01-05 12:20:21', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('eab7bce5be6e4a679e49b3703a1b9798', 'admin', '2019-01-18 16:40:33', '2019-01-18 16:41:23', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('ef11f0f7604444318e2524cd5ecb8fb1', 'admin', '2019-01-18 16:00:15', '2019-01-18 16:07:07', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('f01a2cff1cf447a0bf1ca2510b6fe2e7', 'admin', '2019-01-05 23:43:28', '2019-01-05 15:44:11', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('f3b36238dc9640f1a064468ebd4ed66a', 'admin', '2019-01-23 11:32:45', '2019-01-23 11:33:27', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('f426bcee42524cd08a8bad690a0ec4da', 'admin', '2019-01-08 22:17:44', '2019-01-08 14:17:45', '0:0:0:0:0:0:0:1', '局域网地址', 'OS X', 'Safari 11.1', null, null, null, null, null);
INSERT INTO `l_login_notes` VALUES ('f53bad4d5f0d48a8bde198e18934829f', 'admin', '2019-01-05 21:06:23', '2019-01-05 13:06:58', '0:0:0:0:0:0:0:1', '局域网地址', 'Windows', 'Chrome 69.0.3497.100', null, null, null, null, null);

-- ----------------------------
-- Table structure for l_menu
-- ----------------------------
DROP TABLE IF EXISTS `l_menu`;
CREATE TABLE `l_menu` (
  `id` varchar(40) NOT NULL,
  `pid` varchar(40) DEFAULT NULL COMMENT '父节点id',
  `menu_name` varchar(255) DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(255) DEFAULT NULL COMMENT '跳转链接',
  `type` varchar(255) DEFAULT NULL COMMENT '目录类型',
  `perms` varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `orders` int(5) DEFAULT NULL COMMENT '排序',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_menu
-- ----------------------------
INSERT INTO `l_menu` VALUES ('0dc169c66f5b42ea96d4f450cb061d7c', '2', '跳转icon选择页面', '/menu/iconPage', '2', 'menu:iconPage', 'fa fa-microchip', '3', 'admin', '2019-01-18 16:18:17', null, null);
INSERT INTO `l_menu` VALUES ('1', '0', '网站管理', '/xxx', '0', 'xxx:aaa:sss', 'fa fa-television', '0', null, '2018-12-15 22:45:12', null, '2018-12-15 22:45:12');
INSERT INTO `l_menu` VALUES ('135d1426e8cf4265abc969c280512b71', '2', '菜单删除', '/menu/deleteMenu', '2', 'menu:deleteMenu', 'fa fa-address-book-o', '5', 'admin', '2019-01-18 16:18:54', null, null);
INSERT INTO `l_menu` VALUES ('1f49af396b2542c6a94d068f71e30b7e', '2', '菜单树 页面', '/menu/menuTree', '2', 'menu:menuTree', 'fa fa-address-book', '2', 'admin', '2019-01-18 16:17:33', null, null);
INSERT INTO `l_menu` VALUES ('2', '3', '菜单管理', '/menu', '1', 'menu:index', 'fa fa-bandcamp', '0', null, '2019-01-18 07:14:16', null, '2019-01-18 07:14:16');
INSERT INTO `l_menu` VALUES ('26b0bda93dd34294b68cecab7e1cdb02', '3', '用户管理', '/list', '1', 'user:list', 'fa fa-id-card-o', '1', 'admin', '2019-01-18 16:33:05', null, null);
INSERT INTO `l_menu` VALUES ('2b160e03558a4136a0b2f3797ce45d9a', '3', '登录日志', '/loginLog/index', '1', 'loginLog:list', 'fa fa-bandcamp', '11', 'admin', '2019-01-18 16:24:31', null, null);
INSERT INTO `l_menu` VALUES ('3', '0', '系统管理', '', '0', 'aaa', 'fa fa-eercast', '-999', null, '2019-01-18 16:13:13', null, null);
INSERT INTO `l_menu` VALUES ('33e454ef9804478e9c21001e01d3be3c', '9f7e5d9ad10e40d2a1b54738bd52030d', '字典编辑', '/dict/edit', '2', 'dict:edit', 'fa fa-etsy', '5', 'admin', '2019-01-18 16:26:16', null, null);
INSERT INTO `l_menu` VALUES ('3e020b06d2e24d92ac0b67f6c9a5e846', 'fca0d0c3b9df491ebbee198d5ebeb542', '角色新增', '/role/addPage', '2', 'role:save', '', '1', 'admin', '2019-01-18 16:25:49', null, null);
INSERT INTO `l_menu` VALUES ('4', '1', '友链管理', 'http://www.baidu.com', '1', 'ss', '\r\nla la-gift', '0', null, '2018-12-12 19:22:09', null, '2018-12-12 19:22:09');
INSERT INTO `l_menu` VALUES ('4db0845aa3a84f5d9b483fa45f848ec0', 'fca0d0c3b9df491ebbee198d5ebeb542', '通过 userId 查询 所有角色集合 ', '/role/queryRoleListByUserIdExcludeOwned', '2', 'role:queryRoleListByUserIdExcludeOwned', 'fa fa-s15', '8', 'admin', '2019-01-18 16:32:32', null, null);
INSERT INTO `l_menu` VALUES ('4e777cc9b28f437e90f1656cba4dd503', '26b0bda93dd34294b68cecab7e1cdb02', '用户编辑', '/user/edit', '2', 'user:edit', '', '2', 'admin', '2019-01-18 16:36:54', null, null);
INSERT INTO `l_menu` VALUES ('5e44069c8e144d87bf5160b24c2787a3', 'fca0d0c3b9df491ebbee198d5ebeb542', '保存角色及菜单关系', '/role/save', '2', 'role:save', 'fa fa-thermometer-0', '6', 'admin', '2019-01-18 16:30:19', null, null);
INSERT INTO `l_menu` VALUES ('6bdb3bac10914795b16f77b2fa4d65e0', '-1', '用户基础信息', '/user/baseInfo', '2', 'user:baseInfo', '', '2', 'admin', '2019-01-18 16:39:04', null, null);
INSERT INTO `l_menu` VALUES ('730a2ab87ba04cb3b52749c140997531', '9f7e5d9ad10e40d2a1b54738bd52030d', '字典新增', '/dict/addPage', '2', 'dict:saveDict', 'fa fa-microchip', '1', 'admin', '2019-01-18 16:21:54', null, null);
INSERT INTO `l_menu` VALUES ('85ece7259e61487e9b125a6665dbaa5d', '3', 'oss上传', '/oss/paiUploadFile', '2', 'oss:paiUploadFile', 'fa fa-bandcamp', '1', 'admin', '2019-01-18 21:20:02', null, null);
INSERT INTO `l_menu` VALUES ('8837cb1f72c9406d9da98727002d20ac', '26b0bda93dd34294b68cecab7e1cdb02', '用户详情', '/user/detail', '2', 'user:detail', '', '2', 'admin', '2019-01-18 16:35:44', null, '2019-01-18 16:35:44');
INSERT INTO `l_menu` VALUES ('9f7e5d9ad10e40d2a1b54738bd52030d', '3', '字典管理', '/dict', '1', 'dict:index', 'fa fa-openid', '1', null, '2019-01-18 07:13:36', null, '2019-01-18 07:13:36');
INSERT INTO `l_menu` VALUES ('9fc0d935b62e453b96d5e51836514234', '26b0bda93dd34294b68cecab7e1cdb02', '用户批量删除', '/batchDelete', '2', 'user:batchDelete', '', '1', 'admin', '2019-01-18 16:34:03', null, null);
INSERT INTO `l_menu` VALUES ('a12635a746164df4993aef49f691cea0', 'fca0d0c3b9df491ebbee198d5ebeb542', '角色批量删除', '/role/batchDelete', '2', 'role:batchDelete', 'fa fa-envelope-open', '1', 'admin', '2019-01-18 16:27:12', null, null);
INSERT INTO `l_menu` VALUES ('adb96079dc6f41dab4fbb54a19644f3a', 'fca0d0c3b9df491ebbee198d5ebeb542', '通过角色id查询菜单集合', '/role/queryMenuListByRoleId', '2', 'role:queryMenuListByRoleId', 'fa fa-drivers-license', '6', 'admin', '2019-01-18 16:31:14', null, null);
INSERT INTO `l_menu` VALUES ('baeeff82bbc94bf1b1223f9d437aec94', '26b0bda93dd34294b68cecab7e1cdb02', '用户角色赋予', '/user/transfer', '2', 'user:saveTransfer', '', '6', 'admin', '2019-01-18 16:38:18', null, null);
INSERT INTO `l_menu` VALUES ('be5adc26a5ba47df9b6110886d577a7f', 'c829259df75f440ca08a35e59da2eef1', '数据库监控', '/druid/login.html', '1', 'xxx', 'fa fa-ship', '1', 'admin', '2018-12-13 21:29:45', null, null);
INSERT INTO `l_menu` VALUES ('c829259df75f440ca08a35e59da2eef1', '0', '系统监控', 'x', '0', 'xxx', 'fa fa-wpexplorer', '1', 'admin', '2018-12-15 22:45:17', null, '2018-12-15 22:45:17');
INSERT INTO `l_menu` VALUES ('cc6c4aa986f448eea4d6708f74b9ea9f', '26b0bda93dd34294b68cecab7e1cdb02', '通过用户id查询角色集合', '/role/queryRoleListByUserId', '2', 'role:queryRoleListByUserId', 'fa fa-snowflake-o', '7', 'admin', '2019-01-18 16:35:48', null, '2019-01-18 16:35:48');
INSERT INTO `l_menu` VALUES ('cf4b99fc431c48f491d37600032bce86', '2', '菜单新增', '/menu/menuAdd', '2', 'menu:saveMenu', 'fa fa-bathtub', '1', 'admin', '2019-01-18 16:16:45', null, null);
INSERT INTO `l_menu` VALUES ('cf7f30ca9bf049a196a501dbab376ace', '2', '菜单树数据', '/menu/menuListTree', '2', 'menu:menuListTree', 'fa fa-bandcamp', '1', 'admin', '2019-01-18 16:15:12', null, null);
INSERT INTO `l_menu` VALUES ('e157ab6e8c914216bccc7b4f644208ac', 'fca0d0c3b9df491ebbee198d5ebeb542', '角色编辑', '/role/edit', '2', 'role:edit', 'fa fa-imdb', '3', 'admin', '2019-01-18 16:28:47', null, null);
INSERT INTO `l_menu` VALUES ('e515652682b74bf99891e5bb992ed6b8', '26b0bda93dd34294b68cecab7e1cdb02', '修改密码', 'user/updatePassWordPage', '2', 'user:updatePassWord', '', '5', 'admin', '2019-01-18 16:40:11', null, null);
INSERT INTO `l_menu` VALUES ('ecb48296f31d458881cc5c72ca928b9e', '1', '测试', '', '1', 'sss', 'fa fa-envelope-open', '2', 'admin', '2018-12-27 18:35:27', null, null);
INSERT INTO `l_menu` VALUES ('eeaa24815f0544af82dff21dfd8ca6de', '26b0bda93dd34294b68cecab7e1cdb02', '保存用户', '/user/saveUser', '2', 'user:saveUser', 'fa fa-imdb', '4', 'admin', '2019-01-18 16:37:33', null, null);
INSERT INTO `l_menu` VALUES ('f423b024556344eaaa96824cd17cc5d1', '9f7e5d9ad10e40d2a1b54738bd52030d', '字典批量删除', '/dict/batchDelete', '2', 'dict:batchDelete', 'fa fa-id-card-o', '2', 'admin', '2019-01-18 16:22:55', null, null);
INSERT INTO `l_menu` VALUES ('f493bbbf3d1e416fa1d793aabefa46b7', '2', '菜单编辑', '/menu/edit', '2', 'menu:edit', 'fa fa-imdb', '6', 'admin', '2019-01-18 16:20:38', null, null);
INSERT INTO `l_menu` VALUES ('f5a5f6260eab44388603f72b9a4d8562', 'fca0d0c3b9df491ebbee198d5ebeb542', '角色详情', '/role/detail', '2', 'role:detail', 'fa fa-drivers-license', '2', 'admin', '2019-01-18 16:28:03', null, null);
INSERT INTO `l_menu` VALUES ('fc220cd9b5af4c46976af5c9f4a5b113', '9f7e5d9ad10e40d2a1b54738bd52030d', '字典详情', '/dict/detail', '2', 'dict:detail', 'fa fa-drivers-license', '3', 'admin', '2019-01-18 16:23:33', null, null);
INSERT INTO `l_menu` VALUES ('fca0d0c3b9df491ebbee198d5ebeb542', '3', '角色管理', '/role/index', '1', 'role:index', '', '5', null, '2019-01-18 16:25:14', null, null);
INSERT INTO `l_menu` VALUES ('fcaf9e37d1cc46e4a96dc667c975105e', 'fca0d0c3b9df491ebbee198d5ebeb542', '查询角色集合', '/role/menuList', '2', 'role:menuList', 'fa fa-snowflake-o', '5', 'admin', '2019-01-18 16:29:34', null, null);

-- ----------------------------
-- Table structure for l_role
-- ----------------------------
DROP TABLE IF EXISTS `l_role`;
CREATE TABLE `l_role` (
  `id` varchar(40) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_remk` varchar(255) DEFAULT NULL COMMENT '角色备注',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '角色创建时间',
  `create_user` varchar(255) DEFAULT NULL COMMENT '角色创建人',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user` varchar(40) DEFAULT NULL COMMENT '修改人',
  `del_flag` int(2) DEFAULT NULL COMMENT '0:正常 1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_role
-- ----------------------------
INSERT INTO `l_role` VALUES ('1', 'admin', 'xxx', '2018-12-11 23:05:50', 'admin', null, null, '0');
INSERT INTO `l_role` VALUES ('261aa187693d4c3cb5cef66fc45137fd', 's', 's', '2018-12-07 20:28:45', 'admin', null, null, '1');
INSERT INTO `l_role` VALUES ('3e393ec5ef264fe6a128f23b96e1823e', 'kk', 'kk', '2018-12-17 22:54:14', 'admin', null, null, '1');
INSERT INTO `l_role` VALUES ('43ec448850c54cdb97b51bbc0fb441e8', '测试', '测试', '2019-01-18 19:43:03', 'admin', null, null, '1');
INSERT INTO `l_role` VALUES ('7916278ae696409c855682534eb7a3bd', '测试', '测试', '2019-01-18 19:43:03', 'admin', null, null, '1');
INSERT INTO `l_role` VALUES ('908eb49fb6e94a6cb95522cfb26f2e45', 'cs', 'cs', '2018-12-07 19:49:19', 'admin', null, null, '1');
INSERT INTO `l_role` VALUES ('b3fbfed2ecd6438b8f0601f2e4745119', 'cdd', 'cdd', '2018-12-17 21:03:26', 'admin', null, null, '1');

-- ----------------------------
-- Table structure for l_role_to_menu
-- ----------------------------
DROP TABLE IF EXISTS `l_role_to_menu`;
CREATE TABLE `l_role_to_menu` (
  `id` varchar(40) NOT NULL,
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单id',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_role_to_menu
-- ----------------------------
INSERT INTO `l_role_to_menu` VALUES ('05662feb3c794160a80106e7fbfa3c7f', '261aa187693d4c3cb5cef66fc45137fd', '3', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('0ad1e86c1f0942aebe5d6805c5bd0c32', '1', '4db0845aa3a84f5d9b483fa45f848ec0', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('180da1710d2c4761a2a83c130074fb84', '261aa187693d4c3cb5cef66fc45137fd', '135d1426e8cf4265abc969c280512b71', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('1be9fb737c7d4f4a9484cfbc5f0c5c20', '261aa187693d4c3cb5cef66fc45137fd', '4', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('1e77841f976b4b9d9e2518122b5f2940', '908eb49fb6e94a6cb95522cfb26f2e45', '9f7e5d9ad10e40d2a1b54738bd52030d', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('1f9ffbd14ed64aa6ab32d2fdbb5baaa6', '1', '6bdb3bac10914795b16f77b2fa4d65e0', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('252091397c7b44fba0696b80f4de9d33', '1', '0dc169c66f5b42ea96d4f450cb061d7c', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('3d1f57b88c374642840d70425a485672', '1', 'adb96079dc6f41dab4fbb54a19644f3a', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('466ba9ed8f3b47b0aa7bd5bed3675690', '1', '2b160e03558a4136a0b2f3797ce45d9a', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('4b443d071dd549ebb2a8b513f59bca8d', '1', 'cf4b99fc431c48f491d37600032bce86', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('4e358c242d5448c4a03d3a6130d02594', '261aa187693d4c3cb5cef66fc45137fd', '1', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('5021b319237144f2a36f7ca3a0b6fcd8', '261aa187693d4c3cb5cef66fc45137fd', '9f7e5d9ad10e40d2a1b54738bd52030d', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('6077e6d3ba7e4c2c9bc92d82e4f61ab8', '908eb49fb6e94a6cb95522cfb26f2e45', '08387d106081464483cf386936671084', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('657323a638f1492aa2936836227d55a7', '1', 'baeeff82bbc94bf1b1223f9d437aec94', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('659f9a27085c4c078099af959e614fd8', '1', 'cf7f30ca9bf049a196a501dbab376ace', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('682daaafb7794aac9527eea42d3316ac', '1', '5e44069c8e144d87bf5160b24c2787a3', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('687edbb40d44423a91f1f409b6ee8300', '261aa187693d4c3cb5cef66fc45137fd', 'fca0d0c3b9df491ebbee198d5ebeb542', 'admin', '2019-01-18 20:11:40', null, null);
INSERT INTO `l_role_to_menu` VALUES ('6eb7b7a618fd4a92895f60e8e54cf01e', '1', '135d1426e8cf4265abc969c280512b71', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('6f09003cff4f4deab338eb3b394c976f', '1', 'f5a5f6260eab44388603f72b9a4d8562', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('7606ec77b05547289759fc4e884d4486', '1', '1f49af396b2542c6a94d068f71e30b7e', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('76c1ecc082e24bc29f630564e3f73b21', '1', '9f7e5d9ad10e40d2a1b54738bd52030d', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('78074b4d281a4691a072a7b195e0df3e', '43ec448850c54cdb97b51bbc0fb441e8', '1', null, '2018-12-11 23:09:02', null, '2018-12-11 23:09:02');
INSERT INTO `l_role_to_menu` VALUES ('7dcb91ce0a4243ca8092310b6b585c9f', '1', 'e157ab6e8c914216bccc7b4f644208ac', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('7f009d128c4942d68c12b8e4482bcb3c', '1', '4e777cc9b28f437e90f1656cba4dd503', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('820b894297224d4c8f00ccd378b5bc06', '1', '9fc0d935b62e453b96d5e51836514234', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('82eaa7648e4e46948d9e9a3dbd94f44e', '1', 'cc6c4aa986f448eea4d6708f74b9ea9f', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('857970da328245729922827b2147cc9d', '1', '33e454ef9804478e9c21001e01d3be3c', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('8632c2ac88a142ca9a107c3cfaf0481c', '1', 'fc220cd9b5af4c46976af5c9f4a5b113', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('874465c39ce14072b86a171d9f7b3d8b', '1', 'fcaf9e37d1cc46e4a96dc667c975105e', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('8ec4e11fff0c4a37b698d9d3e5e4164a', '1', '8837cb1f72c9406d9da98727002d20ac', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('8f803eeb6c0549eeb38e4fb1dfb931ee', '1', '4', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('909782b6447c441b89d236f39dd66f96', '1', '26b0bda93dd34294b68cecab7e1cdb02', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('9341b23c22934bf68f0f50d194956a13', '1', 'ecb48296f31d458881cc5c72ca928b9e', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('9607861398e34d59932c67116754d1b3', '908eb49fb6e94a6cb95522cfb26f2e45', '1', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('a219996ff66b41638f65ed1ea00160f7', '1', 'a12635a746164df4993aef49f691cea0', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('a71cb67007134089b07ec58dfa929f7d', '908eb49fb6e94a6cb95522cfb26f2e45', '2', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('a971afed0b1d4722a74e930ec7382c1b', '1', 'eeaa24815f0544af82dff21dfd8ca6de', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('ac4356583f4e4130aa7fef1cf2fddc58', 'b3fbfed2ecd6438b8f0601f2e4745119', '1', 'admin', '2018-12-18 17:06:41', null, null);
INSERT INTO `l_role_to_menu` VALUES ('ad032ce1ce244f5aa536266a6e589825', '1', '3e020b06d2e24d92ac0b67f6c9a5e846', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('b1f4bc9213344f5fb2f479d0b7d7cbb3', '1', 'e515652682b74bf99891e5bb992ed6b8', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('b46b30a9fcf44f23860acd573b8a8994', '7916278ae696409c855682534eb7a3bd', '1', null, '2018-12-11 23:09:04', null, '2018-12-11 23:09:04');
INSERT INTO `l_role_to_menu` VALUES ('ba4da699545a4afdbc9719c2b539d4ad', '3e393ec5ef264fe6a128f23b96e1823e', '1', 'admin', '2018-12-17 22:54:14', null, null);
INSERT INTO `l_role_to_menu` VALUES ('bf3cd289548d439e8a6daf471c26952a', 'b3fbfed2ecd6438b8f0601f2e4745119', '4', 'admin', '2018-12-18 17:06:41', null, null);
INSERT INTO `l_role_to_menu` VALUES ('c0968278bc6e45a2ac443da3dd58f685', '1', '1', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('ca5c6683b06741d69a39321fc7e9b61b', '1', 'f423b024556344eaaa96824cd17cc5d1', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('ce3100dec2404d7b9f56bb134c7bd279', '1', 'fca0d0c3b9df491ebbee198d5ebeb542', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('cf40c6e956f348d0aca631c0109de5e9', '1', '730a2ab87ba04cb3b52749c140997531', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('e0a19812b2944023bf4599a7c77c3ff6', '1', 'be5adc26a5ba47df9b6110886d577a7f', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('e602e0a44daa46fa80a10756068c3852', '1', '2', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('e73ff3d56d464e1ea2fd587081b1788e', '1', '3', 'admin', '2019-01-18 17:19:34', null, null);
INSERT INTO `l_role_to_menu` VALUES ('e9110da4402d4b008f85ffe163354fa2', '3e393ec5ef264fe6a128f23b96e1823e', '4', 'admin', '2018-12-17 22:54:14', null, null);
INSERT INTO `l_role_to_menu` VALUES ('f2ba67bc7ccf4295a675162d94680403', '908eb49fb6e94a6cb95522cfb26f2e45', '4', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('f73dba6785fd4010a8f3b78a275445c5', '908eb49fb6e94a6cb95522cfb26f2e45', '3', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('f9356c60390348a19976cec0dbcb34f0', '908eb49fb6e94a6cb95522cfb26f2e45', 'fca0d0c3b9df491ebbee198d5ebeb542', 'admin', '2018-12-11 21:39:58', null, null);
INSERT INTO `l_role_to_menu` VALUES ('fa77ff8fba314da2a99d65b70b914b2b', '1', 'c829259df75f440ca08a35e59da2eef1', 'admin', '2019-01-18 17:19:35', null, null);
INSERT INTO `l_role_to_menu` VALUES ('fa9a7a8aa97e4ccea9c6d4a89d1eec90', '1', 'f493bbbf3d1e416fa1d793aabefa46b7', 'admin', '2019-01-18 17:19:34', null, null);

-- ----------------------------
-- Table structure for l_user
-- ----------------------------
DROP TABLE IF EXISTS `l_user`;
CREATE TABLE `l_user` (
  `id` varchar(255) NOT NULL,
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `del_flag` int(10) DEFAULT NULL COMMENT '0:正常  1:禁止',
  `last_login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次登录时间',
  `login_count` int(11) DEFAULT NULL COMMENT '登录次数',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_user
-- ----------------------------
INSERT INTO `l_user` VALUES ('1', '埔枘', 'admin', '1f243ecab774af228f909a7213350880', 'http://zprmanager.test.upcdn.net/test/2f58237d-e871-4b0f-b596-b872b1672c60', '854554762@qq.com', '0', '2019-01-23 12:07:45', '182', 'admin', '2019-01-08 14:48:18', null, null);
INSERT INTO `l_user` VALUES ('2', 'sss', 'ddd', null, 'ddd', 'ddd', '1', '2019-01-18 21:45:09', '2', 'admin', '2019-01-18 21:45:09', null, '2019-01-18 21:45:09');

-- ----------------------------
-- Table structure for l_user_to_role
-- ----------------------------
DROP TABLE IF EXISTS `l_user_to_role`;
CREATE TABLE `l_user_to_role` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
  `del_flag` int(1) DEFAULT NULL COMMENT '是否启用:0:启用 1:正常',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of l_user_to_role
-- ----------------------------
INSERT INTO `l_user_to_role` VALUES ('1b080fba7fb541768d64868cef727687', '1', '43ec448850c54cdb97b51bbc0fb441e8', '0', 'admin', '2018-12-13 20:49:24', null, null);
INSERT INTO `l_user_to_role` VALUES ('4ad83093a2e34b28a598200e49e07394', '1', '908eb49fb6e94a6cb95522cfb26f2e45', '0', 'admin', '2018-12-13 20:49:00', null, null);
INSERT INTO `l_user_to_role` VALUES ('72c7348900734a2abdd036747a7bde5b', '1', '7916278ae696409c855682534eb7a3bd', '0', 'admin', '2018-12-13 20:49:24', null, null);
INSERT INTO `l_user_to_role` VALUES ('7d595c03db9d465182ec3ae23c9c52db', '1', '1', '0', 'admin', '2018-12-13 20:49:00', null, null);
INSERT INTO `l_user_to_role` VALUES ('a4c2b0bd6cd94744aac7e57495399fe5', '1', '261aa187693d4c3cb5cef66fc45137fd', '0', 'admin', '2018-12-13 20:49:24', null, null);
INSERT INTO `l_user_to_role` VALUES ('caf73b0901cd4f049e6e1ab2fd69a92c', '2', '1', '0', 'admin', '2018-12-13 20:58:23', null, null);
