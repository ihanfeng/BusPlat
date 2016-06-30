

DROP TABLE IF EXISTS `catptcha`;
CREATE TABLE `catptcha` (
  `id` bigint NOT NULL COMMENT '序号',
  `pic` varchar(255) DEFAULT '' COMMENT '图片' ,
  `value` varchar(255)  DEFAULT '' COMMENT '验证码值',
  `type` varchar(255)  DEFAULT 'default' COMMENT '验证码类型',
  `module` varchar(255)  DEFAULT 'default' COMMENT '验证码所属业务模块',
   `token` varchar(255)  DEFAULT 'default' COMMENT '验证码Token',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `expire_time` datetime DEFAULT NULL COMMENT '失效时间，null代表永久有效',
  `del_status` int DEFAULT 0 COMMENT '删除状态',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;