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
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT, -- 商品编号，整数类型，自增长，主键
    `name` varchar(255) DEFAULT NULL, -- 商品名称，变长字符串
    `price` decimal(10,2) DEFAULT NULL, -- 商品价格，十进制类型，保留两位小数
    `type` varchar(255) DEFAULT NULL, -- 商品类型，变长字符串
    `image` varchar(255) DEFAULT NULL, -- 商品图片，变长字符串
    PRIMARY KEY (`id`) -- 主键约束
);
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT, -- 商品编号，整数类型，自增长，主键
    `name` varchar(255) DEFAULT NULL, -- 商品名称，变长字符串
    `price` decimal(10,2) DEFAULT NULL, -- 商品价格，十进制类型，保留两位小数
    `type` varchar(255) DEFAULT NULL, -- 商品类型，变长字符串
    `image` varchar(255) DEFAULT NULL, -- 商品图片，变长字符串
    `count` int DEFAULT NULL, -- 商品数量，十进制类型，整数
    `username` varchar(255) DEFAULT NULL, -- 用户名
    PRIMARY KEY (`id`) -- 主键约束
);