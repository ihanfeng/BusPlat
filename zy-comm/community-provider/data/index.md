
EXPLAIN select count(*) from zhiyin_community_topic_info where del_status = 0

EXPLAIN select count(*) from zhiyin_community_topic_info where addr_id = 1 and del_status = 0

EXPLAIN select count(*) from zhiyin_community_topic_info where user_id = 1 and del_status = 0

-- create index addr_user on zhiyin_community_topic_info(addr_id,user_id)
-- create index idx_del on zhiyin_community_topic_info(del_status)

show index from zhiyin_community_topic_info