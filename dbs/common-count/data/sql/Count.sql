
CREATE TABLE `article_view`(
    `article_id` int(11) NOT NULL,
    `pond` tinyint(4) NOT NULL COMMENT '池子，就是用来随机用的',
    `view` int(11) NOT NULL,
    PRIMARY KEY (`article_id`, `pond`)
)ENGINE=InnoDB;


INSERT INTO `article_view` (`article_id`, `pond`, `view`) VALUES (`123`, RAND()*100, 1) ON DUPLICATE KEY UPDATE `view`=`view`+1


SELECT SUM(`view`) FROM `article_view` WHERE `article_id`='123'
