import com.alibaba.fastjson.JSON;

import com.github.pagehelper.PageInfo;
import com.zhiyin.device.dbs.DeviceDbsProviderApplication;
import com.zhiyin.device.dbs.config.RedisCacheName;
import com.zhiyin.device.dbs.entity.DeviceFixInfo;
import com.zhiyin.device.dbs.service.IDeviceFixInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DeviceDbsProviderApplication.class)
//@ContextConfiguration(classes = {RedisCacheConfig.class})
public class RedisCachableTest {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    IDeviceFixInfoService deviceFixInfoService;

    @Test
    public void testError() {

        DeviceFixInfo selFromService = deviceFixInfoService.selectById(2L);
        assertThat(selFromService).isNotNull();

    }

    @Test
    public void testSel(){
        Long newId = deviceFixInfoService.insertSelectiveGet(newInfo());
        deviceFixInfoService.selectById(newId);
    }



    @Test
    public void testFind() {

        DeviceFixInfo selFromService = deviceFixInfoService.selectById(2L);
        assertThat(selFromService).isNotNull();

        Cache cache = cacheManager.getCache(RedisCacheName.UserCacheName);
        assertThat(cache).isNotNull();

        DeviceFixInfo selFromCache = (DeviceFixInfo) cache.get(RedisCacheName.UserCacheName + ".id.2").get();
        assertThat(selFromCache.getId()).isEqualTo(selFromService.getId());

    }

    @Test
    public void testDel() {

        Long newId = deviceFixInfoService.insertSelectiveGet(newInfo());




        deviceFixInfoService.deleteByPrimaryKey(2L);
        Cache cache = cacheManager.getCache(RedisCacheName.UserCacheName);
        assertThat(cache).isNotNull();

        Cache.ValueWrapper selFromCache = cache.get(RedisCacheName.UserCacheName + ".id.2");

        assertThat(selFromCache == null);

    }


    @Test
    public void testInsert(){


    }




    @Test
    public void selectAll() {

        PageInfo ret = deviceFixInfoService.selectByPage(1, 20);

        log.info(JSON.toJSONString(ret));
    }


    public DeviceFixInfo newInfo(){
        DeviceFixInfo u = new DeviceFixInfo();
        u.setUuid(UUID.randomUUID().toString());
        return u;
    }
//    @Test
//    public void testUpdate() throws InterruptedException {
//
//        userInfoService.selectAll();
//        Cache cache = cacheManager.getCache(CacheNameFactory.UserSelAll);
//        assertThat(cache).isNotNull();
//
//        deviceFixInfoService.selectById(2L);
//
//        deviceFixInfoService.selectById(3L);
//        userInfoService.updateUser(3L, "hhhhh");
//
//        cache = cacheManager.getCache(CacheNameFactory.UserSelAll);
//        Cache.ValueWrapper selAll = cache.get("com.hg.spring.cache.rediscache.service.impl.UserInfoService.selectAll");
//        assertThat(selAll).isNull();
//
//
//
//    }


}
