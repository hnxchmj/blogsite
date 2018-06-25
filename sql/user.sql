SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) AUTO_INCREMENT,
  `username` varchar(255) NOT NULL ,
  `display_name` varchar(255),
  `email` varchar(255),
  `mobile` varchar(255),
  `is_male` bit,
  `birthday` date,
  `create_time` datetime,
  `update_time` datetime,
  PRIMARY KEY (`id`),
  UNIQUE `unq_username`(`username`),
  UNIQUE `unq_email`(`email`),
  UNIQUE `unq_mobile`(`mobile`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;