/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : saylovewall

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 09/10/2019 20:07:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for love_discuss
-- ----------------------------
DROP TABLE IF EXISTS `love_discuss`;
CREATE TABLE `love_discuss`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `love_link_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `discuss`(`love_link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for love_link
-- ----------------------------
DROP TABLE IF EXISTS `love_link`;
CREATE TABLE `love_link`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `my_user_id` int(11) NOT NULL,
  `to_user_id` int(11) NOT NULL,
  `say_love` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `love_img` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `love_state` int(2) NOT NULL DEFAULT 0,
  `read_state` int(2) NOT NULL DEFAULT 0,
  `love_read` int(50) NULL DEFAULT 0,
  `love_like` int(50) NULL DEFAULT 0,
  `love_dis` int(11) NULL DEFAULT 0,
  `love_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `my`(`my_user_id`) USING BTREE,
  INDEX `to`(`to_user_id`) USING BTREE,
  CONSTRAINT `my` FOREIGN KEY (`my_user_id`) REFERENCES `yb_user` (`yb_userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 191 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user_like
-- ----------------------------
DROP TABLE IF EXISTS `user_like`;
CREATE TABLE `user_like`  (
  `love_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  INDEX `wai_love_id`(`love_id`) USING BTREE,
  INDEX `wai_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `wai_user_id` FOREIGN KEY (`user_id`) REFERENCES `yb_user` (`yb_userid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for yb_user
-- ----------------------------
DROP TABLE IF EXISTS `yb_user`;
CREATE TABLE `yb_user`  (
  `yb_userid` int(11) NOT NULL,
  `yb_username` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yb_usernick` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `yb_sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `yb_money` int(11) NULL DEFAULT NULL,
  `yb_exp` int(11) NULL DEFAULT NULL,
  `yb_userhead` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yb_schoolid` int(11) NULL DEFAULT NULL,
  `yb_schoolname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `yb_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`yb_userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of yb_user
-- ----------------------------
INSERT INTO `yb_user` VALUES (5201314, 'test', '测试用户', '1', 1, 1, 'http://img1.imgtn.bdimg.com/it/u=1624240531,2195794812&fm=26&gp=0.jpg', 1, '1', '2019-10-09 20:03:06');

-- ----------------------------
-- Procedure structure for attitude
-- ----------------------------
DROP PROCEDURE IF EXISTS `attitude`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `attitude`(IN `in_name` varchar(50),IN `in_love_id` INT)
BEGIN
	
	IF in_name="agree" THEN
	UPDATE `saylovewall`.`love_link` SET `love_state` = 1 WHERE `id` = in_love_id;
	UPDATE `saylovewall`.`love_link` SET `read_state` = 1 WHERE `id` = in_love_id;
ELSE 
	UPDATE `saylovewall`.`love_link` SET `love_state` = -1 WHERE `id` = in_love_id;
	UPDATE `saylovewall`.`love_link` SET `read_state` = 1 WHERE `id` = in_love_id;
END IF;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for delete_discuss
-- ----------------------------
DROP PROCEDURE IF EXISTS `delete_discuss`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_discuss`(IN in_id INT)
BEGIN
	
	UPDATE `saylovewall`.`love_link` SET `love_dis` = love_dis-1 WHERE `id` = ANY(SELECT love_link_id FROM love_discuss WHERE id = in_id);
	DELETE FROM `saylovewall`.`love_discuss` WHERE `id` = in_id;
	
	
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for find_love
-- ----------------------------
DROP PROCEDURE IF EXISTS `find_love`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `find_love`(IN `sql_name` varchar(255),IN `in_love_id` int)
BEGIN
	
	IF sql_name="my" THEN
	SELECT love_link.id,love_link.my_user_id,love_link.to_user_id,love_link.say_love,love_link.love_img,love_link.love_state,love_link.read_state,love_link.love_read,love_link.love_like,love_link.love_time,y1.yb_username,y1.yb_schoolname,y1.yb_userhead,y2.yb_username AS to_username,y2.yb_schoolname AS to_schoolname,y2.yb_userhead AS to_userhead FROM love_link LEFT OUTER JOIN yb_user AS y1 ON love_link.my_user_id=y1.yb_userid LEFT OUTER JOIN  yb_user AS y2 ON 		love_link.to_user_id=y2.yb_userid WHERE my_user_id =in_love_id ;
	ELSE
		SELECT love_link.id,love_link.my_user_id,love_link.to_user_id,love_link.say_love,love_link.love_img,love_link.love_state,love_link.read_state,love_link.love_read,love_link.love_like,love_link.love_time,y1.yb_username,y1.yb_schoolname,y1.yb_userhead,y2.yb_username AS to_username,y2.yb_schoolname AS to_schoolname,y2.yb_userhead AS to_userhead FROM love_link LEFT OUTER JOIN yb_user AS y1 ON love_link.my_user_id=y1.yb_userid LEFT OUTER JOIN  yb_user AS y2 ON 		love_link.to_user_id=y2.yb_userid WHERE to_user_id =in_love_id ;
	END IF;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for insert_discuss
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert_discuss`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_discuss`(IN in_love_id int,IN in_user_id int,IN in_content VARCHAR(255)character set utf8)
BEGIN
	
	INSERT INTO `saylovewall`.`love_discuss`(`love_link_id`, `user_id`, `content`) VALUES (in_love_id, in_user_id, in_content);
	UPDATE `saylovewall`.`love_link` SET `love_dis` = love_dis+1 WHERE `id` = in_love_id;
	
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for raise_read
-- ----------------------------
DROP PROCEDURE IF EXISTS `raise_read`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `raise_read`(IN `in_love_id` int)
BEGIN
	
	UPDATE love_link SET  love_read  = love_read+1  WHERE  id  = in_love_id;
	SELECT love_read FROM love_link WHERE id=in_love_id;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for suport
-- ----------------------------
DROP PROCEDURE IF EXISTS `suport`;
delimiter ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `suport`(IN `in_user_id` int,IN `in_love_id` int)
BEGIN
	
	declare a int(1) default 0;
	SELECT COUNT(*) INTO a FROM user_like WHERE user_id=in_user_id AND love_id= in_love_id ;

	IF a>0 THEN
	DELETE FROM user_like WHERE love_id= in_love_id AND user_id= in_user_id;
	UPDATE `saylovewall`.`love_link` SET `love_like` = love_like-1 WHERE `id` = in_love_id;
	ELSE 
	INSERT INTO `saylovewall`.`user_like`(`love_id`, `user_id`) VALUES (in_love_id, in_user_id);
	UPDATE `saylovewall`.`love_link` SET `love_like` = love_like+1 WHERE `id` = in_love_id;
	END IF;
	SELECT love_like FROM love_link WHERE id=in_love_id;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
