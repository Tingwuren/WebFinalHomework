DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `username` varchar(255) DEFAULT NULL,
   `password` varchar(255) DEFAULT NULL,
   `number` varchar(255) DEFAULT NULL,
   `email` varchar(255) DEFAULT NULL,
   `avatar` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
);