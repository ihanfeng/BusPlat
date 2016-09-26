import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

/**
 * Created by wangqinghui on 2016/9/26.
 */
public class GuavaCacheStat {

    public static void main(String[] args) {

        Cache<String,String> cache =  CacheBuilder.newBuilder().recordStats().build();


        CacheStats cacheStats = cache.stats();

        cache.getIfPresent("aa");

        System.out.println(        cacheStats.hitCount());
    }
}
