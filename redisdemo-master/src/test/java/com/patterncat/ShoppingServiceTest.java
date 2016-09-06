package com.patterncat;

import com.patterncat.service.ShoppingService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import redis.clients.jedis.Tuple;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by patterncat on 2016-02-11.
 */
public class ShoppingServiceTest extends RedisdemoApplicationTests{

    @Autowired
    ShoppingService shoppingService;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void loginCookies() throws InterruptedException {
        System.out.println("\n----- testLoginCookies -----");
        String token = UUID.randomUUID().toString();

        shoppingService.viewItem(token, "username", "itemX");
        System.out.println("We just logged-in/updated token: " + token);
        System.out.println("For user: 'username'");
        System.out.println();

        System.out.println("What username do we get when we look-up that token?");
        String r = shoppingService.getLgoinUserByToken(token);
        System.out.println(r);
        System.out.println();
        Assert.assertNotNull(r);

        System.out.println("Let's drop the maximum number of cookies to 0 to clean them out");
        System.out.println("We will start a thread to do the cleaning, while we stop it later");

        shoppingService.startCleanSessionTask();

        long s = redisTemplate.opsForHash().size(ShoppingService.KEY_LOGIN_USER);
        System.out.println("The current number of sessions still available is: " + s);
        Assert.assertTrue(s == 0);
    }

    @Test
    public void shoppingCartCookies() throws InterruptedException {
        System.out.println("\n----- testShopppingCartCookies -----");
        String token = UUID.randomUUID().toString();

        System.out.println("We'll refresh our session...");
        shoppingService.viewItem(token, "username", "itemX");
        System.out.println("And add an item to the shopping cart");
        shoppingService.addToCart(token, "itemY", 3);
        Map<String,String> r = redisTemplate.opsForHash().entries(shoppingService.formUserCartKey(token));
        System.out.println("Our shopping cart currently has:");
        for (Map.Entry<String,String> entry : r.entrySet()){
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();

        Assert.assertTrue(r.size() >= 1);

        System.out.println("Let's clean out our sessions and carts");

        shoppingService.startCleanSessionTask();

        r = redisTemplate.opsForHash().entries(shoppingService.formUserCartKey(token));
        System.out.println("Our shopping cart now contains:");
        for (Map.Entry<String,String> entry : r.entrySet()){
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        Assert.assertTrue(r.size() == 0);
    }

    @Test
    public void cacheRequest(){
        System.out.println("\n----- testCacheRequest -----");
        String token = UUID.randomUUID().toString();

        shoppingService.viewItem(token, "username", "itemX");
        String url = "http://test.com/?item=itemX";
        System.out.println("We are going to cache a simple request against " + url);
        String result = shoppingService.cacheRequest(url, () -> "content for " + url);
        System.out.println("We got initial content:\n" + result);
        System.out.println();

        Assert.assertNotNull(result);

        System.out.println("To test that we've cached the request, we'll pass a bad callback");
        String result2 = shoppingService.cacheRequest(url, null);
        System.out.println("We ended up getting the same response!\n" + result2);

        Assert.assertTrue(result.equals(result2));

        Assert.assertFalse(shoppingService.canCache("http://test.com/"));
        Assert.assertFalse(shoppingService.canCache("http://test.com/?item=itemX&_=1234536"));
    }

    @Test
    public void cacheRows() throws InterruptedException {
        System.out.println("\n----- testCacheRows -----");
        System.out.println("First, let's schedule caching of itemX every 5 seconds");
        shoppingService.scheduleRowCache("itemX", 5);
        System.out.println("Our schedule looks like:");
        Set<ZSetOperations.TypedTuple> range = redisTemplate.boundZSetOps(ShoppingService.KEY_SCHEDULE).rangeWithScores(0, -1);
        for (ZSetOperations.TypedTuple tuple : range){
            System.out.println("  " + tuple.getValue() + ", " + tuple.getScore());
        }
        Assert.assertTrue(range.size() != 0);

        System.out.println("We'll start a caching thread that will cache the data...");

        shoppingService.startCacheRowTask();

        Thread.sleep(1000);
        System.out.println("Our cached data looks like:");
        String r = (String) redisTemplate.opsForValue().get(shoppingService.formInventoryKey("itemX"));
        System.out.println(r);
        Assert.assertNotNull(r);
        System.out.println();

        System.out.println("We'll check again in 5 seconds...");
        Thread.sleep(5000);
        System.out.println("Notice that the data has changed...");
        String r2 = (String) redisTemplate.opsForValue().get(shoppingService.formInventoryKey("itemX"));
        System.out.println(r2);
        System.out.println();
        Assert.assertNotNull(r2);
        Assert.assertFalse(r.equals(r2));

        System.out.println("Let's force un-caching");
        shoppingService.scheduleRowCache("itemX", -1);
        Thread.sleep(1000);
        r = (String) redisTemplate.opsForValue().get(shoppingService.formInventoryKey("itemX"));
        System.out.println("The cache was cleared? " + (r == null));
        Assert.assertNull(r);
    }
}
