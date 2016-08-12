//package com.hg.ratelimiter.controller;
//import java.util.concurrent.Callable;
//
//
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//
//import javax.xml.ws.Response;
//
//import org.slf4j.Logger;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.StringUtils;
//
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import com.google.common.util.concurrent.RateLimiter;
//
//@Controller
//public class APIRateLimiter  {
//	
//    private static Cache<String,RateLimiter> cache= CacheBuilder.newBuilder().initialCapacity(1000)
//            .expireAfterAccess(10, TimeUnit.MINUTES).build();
//    //qps 60
//    private static final double DEFAULT_LIMIT=60;
//    @Before
//    public static void rateLimiting() {
//        Response response = new Response();
//        String access_key_id = request.params.get("access_key_id");
//        if (StringUtils.isEmpty(access_key_id)) {
//            response.setParameterMiss("access_key_id");
//            renderText(response.toJSON());
//        }
//        RateLimiter rateLimiter= null;
//        try {
//            rateLimiter = cache.get(access_key_id, new Callable<RateLimiter>() {
//                @Override
//                public RateLimiter call() throws Exception {
//                    return RateLimiter.create(DEFAULT_LIMIT);
//                }
//            });
//        } catch (ExecutionException e) {
//            Logger.error("get rate limiter error",e);
//        }
//        if(!rateLimiter.tryAcquire()) { //未请求到limiter则返回超额提示
//            response.setCodeWithDefaultMsg(ResponseCode.CLIENT_OVER_QUOTA);
//            renderText(response.toJSON());
//        }
//    }
//}