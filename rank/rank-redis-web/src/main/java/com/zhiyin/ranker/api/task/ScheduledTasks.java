package com.zhiyin.ranker.api.task;

import com.zhiyin.ranker.lb.LeaderboardTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Lazy(false)
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    private LeaderboardTemplate demoLb;
    @Autowired
    private StringRedisTemplate template;

    @Scheduled(fixedRate = 1000 * 60)
    public void reportCurrentTime() {
//        template.getConnectionFactory().getConnection().
        template.opsForValue().set("a", "a");
        System.out.println("当前时间：" + dateFormat.format(new Date()));
    }

    @Scheduled(fixedRate = 1000 * 5)
    public void tLb() {
        for (int i = 1; i <= 10; i++) {
            demoLb.rankMember("member_" + i, i);
        }
        demoLb.aroundMe("a", false);
    }

}
