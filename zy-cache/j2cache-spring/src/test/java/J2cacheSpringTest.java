import com.alibaba.fastjson.JSON;
import com.zhiyin.j2cache.Account;
import com.zhiyin.j2cache.AccountService;
import com.zhiyin.j2cache.J2cacheCacheConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {J2cacheCacheConfig.class})

public class J2cacheSpringTest {

    @Resource
    AccountService accountService;

    @Test
    public void test() {

        Account account = null ;

        // 第一次查询，应该走数据库
        System.out.println("first query...");
        account = accountService.getAccountByName("somebody");
        System.out.println("query result:" + JSON.toJSONString(account));

        // 第二次查询，应该不查数据库，直接返回缓存的值
        System.out.println("second query...");
        account = accountService.getAccountByName("somebody");
        System.out.println("query result:" + JSON.toJSONString(account));
    }
}