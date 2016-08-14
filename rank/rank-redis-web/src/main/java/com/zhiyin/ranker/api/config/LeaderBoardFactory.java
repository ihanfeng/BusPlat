package com.zhiyin.ranker.api.config;

import com.zhiyin.ranker.lb.LeaderboardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by hg on 2016/8/1.
 */
@Component
public class LeaderBoardFactory {

    @Autowired
    private StringRedisTemplate template;

    @Bean(name = "demoLb")
    public LeaderboardTemplate demoLb() {
        LeaderboardTemplate lb = new LeaderboardTemplate("leaderboard_demo");
        lb.setTemplate(template);
        return lb;
    }
}
