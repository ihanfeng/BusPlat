package com.hg.spring.cache.rediscache;

import com.alibaba.fastjson.JSON;
import com.hg.spring.cache.rediscache.config.RedisCacheName;
import com.hg.spring.cache.rediscache.entity.User;
import com.hg.spring.cache.rediscache.service.IUserInfoService;
import com.hg.spring.cache.rediscache.service.impl.CacheNameFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
//@ContextConfiguration(classes = {RedisCacheConfig.class})
public class RedisCachableTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    IUserInfoService userInfoService;

    @Test
    public void testError() {

        User selFromService = userInfoService.findById(2L);
        assertThat(selFromService).isNotNull();


    }

    @Test
    public void testFind() {

        User selFromService = userInfoService.findById(2L);
        assertThat(selFromService).isNotNull();

        Cache cache = cacheManager.getCache(RedisCacheName.UserCacheName);
        assertThat(cache).isNotNull();

        User selFromCache = (User) cache.get(RedisCacheName.UserCacheName + ".id.2").get();
        assertThat(selFromCache.getName()).isEqualTo(selFromService.getName());

    }

    @Test
    public void testDel() {

        User selFromService = userInfoService.findById(2L);
        assertThat(selFromService).isNotNull();

        userInfoService.deleteById(2L);
        Cache cache = cacheManager.getCache(RedisCacheName.UserCacheName);
        assertThat(cache).isNotNull();

        Cache.ValueWrapper selFromCache = cache.get(RedisCacheName.UserCacheName + ".id.2");

        assertThat(selFromCache == null);

    }


    @Test
    public void testInsert(){

        User u = new User();
        u.setId(11L);
        u.setName("hg");
        u.setAge(10);

        userInfoService.insert(u);
    }




    @Test
    public void selectAll() {

        List<User> ret = userInfoService.selectAll();

        log.info(JSON.toJSONString(ret));
    }

    @Test
    public void testUpdate() throws InterruptedException {

        userInfoService.selectAll();
        Cache cache = cacheManager.getCache(CacheNameFactory.UserSelAll);
        assertThat(cache).isNotNull();

        userInfoService.findById(2L);

        userInfoService.findById(3L);
        userInfoService.updateUser(3L, "hhhhh");

        cache = cacheManager.getCache(CacheNameFactory.UserSelAll);
        Cache.ValueWrapper selAll = cache.get("com.hg.spring.cache.rediscache.service.impl.UserInfoService.selectAll");
        assertThat(selAll).isNull();


//        Cache cache = cacheManager.getCache("userCache");
//        assertThat(cache).isNotNull();
//
//        User selFromCache = (User) cache.get("userinfo_id_2").get();
//        assertThat( selFromCache.getName() ).isEqualTo( selFromService.getName() );

    }


}
