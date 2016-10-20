
CREATE TABLE `zy_user_2` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(80) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` char(60) NOT NULL DEFAULT '' COMMENT '密码',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账号表';


CREATE TABLE `zy_user_social` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `open_id` varchar(120) NOT NULL DEFAULT '' COMMENT '社交平台开放ID',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `channel_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID(1=QQ,2=SinaWeibo,3=Weixin)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户社交账号表';



CREATE TABLE `zy_user_login_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '用户ID',
  `platform_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '平台ID(1=PC,2=Wap,3=Android,4=iOS)',
  `channel_id` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '渠道ID(1=QQ,2=SinaWeibo,3=Weixin)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  `del_status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录日志表';

