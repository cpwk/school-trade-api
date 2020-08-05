/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : school_trade

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 12/05/2020 15:30:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '收货人手机号码',
  `code` varchar(6) DEFAULT NULL COMMENT '地区代码',
  `detail` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `def` tinyint(1) DEFAULT NULL COMMENT '默认地址：1.默认地址2.非默认地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `name` varchar(16) DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `role_id` int(11) DEFAULT NULL COMMENT '权限组ID',
  `status` tinyint(1) DEFAULT NULL COMMENT '管理员状态：1.默认2.删除3.停用4.取消',
  `signin_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `avatar` varchar(100) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_session
-- ----------------------------
DROP TABLE IF EXISTS `admin_session`;
CREATE TABLE `admin_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员ID',
  `role` varchar(16) DEFAULT NULL COMMENT '权限组名称',
  `name` varchar(128) DEFAULT NULL COMMENT '管理员名称',
  `token` varchar(64) DEFAULT NULL COMMENT '登录标识',
  `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
  `expire_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `ip` varchar(32) DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.启用2.禁用',
  `priority` int(11) DEFAULT NULL COMMENT '权重',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `img` varchar(1024) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for car
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `product_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `num` int(11) DEFAULT NULL COMMENT '商品数量',
  `sno` int(11) DEFAULT NULL COMMENT '商品规格号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `product_id` int(11) DEFAULT NULL COMMENT '商品ID',
  `orders_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `created_at` bigint(30) DEFAULT NULL COMMENT '创建时间',
  `content` varchar(1024) DEFAULT NULL COMMENT '评论内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for coupon
-- ----------------------------
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` decimal(10,0) DEFAULT NULL COMMENT '价值',
  `code` varchar(6) DEFAULT NULL COMMENT '购物券编码',
  `duration` int(20) DEFAULT NULL COMMENT '持续时长',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.启用2.禁用',
  `img` varchar(1024) DEFAULT NULL COMMENT '封面图',
  `rule` varchar(5000) DEFAULT NULL COMMENT '优惠规则',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `coupon_user`;
CREATE TABLE `coupon_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠券ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `get_at` bigint(20) DEFAULT NULL COMMENT '领取时间',
  `expir_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.待使用2.已过期3.已使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for merchant
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `logo` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图标',
  `state` tinyint(1) NOT NULL COMMENT '状态：1.正常2.禁用3.欠费',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `product_category_sequences` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品类型',
  `valid_thru` bigint(20) NOT NULL COMMENT '到期时间',
  `setting` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '是否开启独立公众号：1.开启2.不开启',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101007 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for merchant_admin
-- ----------------------------
DROP TABLE IF EXISTS `merchant_admin`;
CREATE TABLE `merchant_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `merchant_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `name` varchar(30) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '名称',
  `mobile` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.默认2.删除3.停用4.取消',
  `created_at` bigint(20) NOT NULL COMMENT '创建时间',
  `signin_at` bigint(20) DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_num` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `total` bigint(20) DEFAULT NULL COMMENT '总计',
  `address` varchar(10000) DEFAULT NULL COMMENT '收货详细地址',
  `products` varchar(10000) DEFAULT NULL COMMENT '商品',
  `created_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL COMMENT '名称',
  `sort_id` int(20) DEFAULT NULL COMMENT '类型ID',
  `status` tinyint(1) DEFAULT NULL COMMENT '上下架状态：1.上架2.下架',
  `content` longtext COMMENT '详细介绍',
  `product_items` varchar(2048) DEFAULT NULL COMMENT '商品规格',
  `priority` int(11) DEFAULT '0' COMMENT '权重',
  `state` tinyint(1) DEFAULT NULL COMMENT '审核状态：1待审核2.已通过3.未通过',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL COMMENT '名称',
  `permissions` varchar(1024) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(16) DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级分类ID',
  `priority` int(11) DEFAULT NULL COMMENT '权重',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.启用2.禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(20) NOT NULL COMMENT '手机号码',
  `email` varchar(20) DEFAULT NULL COMMENT '电子邮箱',
  `nick` varchar(20) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `created_at` bigint(20) DEFAULT NULL COMMENT '注册时间',
  `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
  `balance` decimal(20,0) DEFAULT NULL COMMENT '账户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_session
-- ----------------------------
DROP TABLE IF EXISTS `user_session`;
CREATE TABLE `user_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `token` varchar(100) NOT NULL COMMENT '登录标识',
  `signin_at` bigint(20) NOT NULL COMMENT '登录时间',
  `expire_at` bigint(20) NOT NULL COMMENT '过期时间',
  `ip` varchar(100) DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=235 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip
-- ----------------------------
DROP TABLE IF EXISTS `vip`;
CREATE TABLE `vip` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `grade` tinyint(4) DEFAULT NULL COMMENT '等级',
  `intro` varchar(512) DEFAULT NULL COMMENT '简介',
  `price_rule` varchar(512) DEFAULT NULL COMMENT '规则',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态：1.启用2.禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vip_user
-- ----------------------------
DROP TABLE IF EXISTS `vip_user`;
CREATE TABLE `vip_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sno` tinyint(4) DEFAULT NULL COMMENT 'VIP规格号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `vip_id` int(11) DEFAULT NULL COMMENT 'VIP的ID',
  `created_at` bigint(30) DEFAULT NULL COMMENT '开通时间',
  `expire_at` bigint(20) DEFAULT NULL COMMENT '过期时间',
  `remark` varchar(1024) DEFAULT NULL COMMENT '开通信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
