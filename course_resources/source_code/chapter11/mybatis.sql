CREATE TABLE `t_user`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ;
INSERT INTO `t_user` VALUES (1, '张三');
INSERT INTO `t_user` VALUES (2, '李四');
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  `price` int(0) NOT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ;
INSERT INTO `t_order` VALUES (1, 2, 123);
INSERT INTO `t_order` VALUES (2, 2, 456);
INSERT INTO `t_order` VALUES (3, 1, 789);