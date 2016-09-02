package com.patterncat;

import com.patterncat.bean.ArticleBean;
import com.patterncat.service.VoteService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by patterncat on 2016-02-11.
 */
public class VoteServiceTest extends RedisdemoApplicationTests{

    @Autowired
    VoteService voteService;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void incrMap(){
        //template.setHashValueSerializer(new StringRedisSerializer(StandardCharsets.UTF_8));
        //这里得用string写进去
//        redisTemplate.<String,Object>opsForHash().put("user-abcdef", "count", "1");
        //error java.lang.ClassCastException: java.lang.Long cannot be cast to java.lang.String
//        redisTemplate.<String,Object>opsForHash().put("user-abcde", "now", System.currentTimeMillis());
//        System.out.println(redisTemplate.opsForHash().get("user-abcde", "now"));


        //template.setHashValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        redisTemplate.opsForHash().increment("user-abcdef", "count", 5);

    }

    @Test
    public void delete(){
        for(int i=0;i<100;i++){
            voteService.delete(i+1L);
        }
        redisTemplate.delete("articleIdSeq");
    }

    @Test
    public void post() throws InterruptedException {
        for(int i=0;i<100;i++){
            voteService.postArticle("user" + i, "title" + i, "link" + i);
            Thread.sleep(1000);
        }
        for(int i=0;i<100;i++){
            ArticleBean bean  = voteService.getArticleById(i + 1L);
            System.out.println(ToStringBuilder.reflectionToString(bean));
        }
    }

    @Test
    public void vote(){
        Random random = new Random();
        IntStream.range(1,31).forEach(x -> {
            Long articleId = random.nextInt(100) + 1L;
            String user = "user" + (random.nextInt(100) + 1);
            voteService.voteArticle(user,articleId);
        });
    }

    @Test
    public void addTag(){
        Random random = new Random();
        IntStream.range(1,31).forEach(x -> {
            Long articleId = random.nextInt(100) + 1L;
            String tag = "tag" + (random.nextInt(5) + 1);
            voteService.tagArticle(articleId, new String[]{tag});
        });
    }

    @Test
    public void getByScore(){
        List<ArticleBean> data = voteService.getArticleRankByScore(1);
        data.stream().forEach(System.out::println);
    }

    @Test
    public void getByTime(){
        List<ArticleBean> data = voteService.getArticleRankByTime(1);
        data.stream().forEach(System.out::println);
    }

    @Test
    public void getByTag(){
        List<ArticleBean> data = voteService.getArticleRankByTag(1, VoteService.KEY_SCORE,"tag1");
        data.stream().forEach(System.out::println);
    }
}
