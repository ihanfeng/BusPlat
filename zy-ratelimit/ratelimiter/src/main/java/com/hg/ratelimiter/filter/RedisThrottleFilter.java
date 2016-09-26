package com.hg.ratelimiter.filter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangqinghui on 2016/9/23.
 */
@Slf4j
public class RedisThrottleFilter implements Filter{


    private static Cache<String,RateLimiter> cache= CacheBuilder.newBuilder().initialCapacity(1000)
            .expireAfterAccess(10, TimeUnit.MINUTES).build();
    //qps 60
    private static final double DEFAULT_LIMIT=60;


    private KeyGen keyGen = new DefaultKeyGen();




    public void init(FilterConfig cfg) {
        ApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(cfg.getServletContext());
//        this.bean = ctx.getBean(YourBeanType.class);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        if(request instanceof HttpServletRequest){

            HttpServletRequest httpReq = (HttpServletRequest) request;

            String key = keyGen.gen(new WebReq(httpReq));
            RateLimiter rateLimiter= null;
            try {
                rateLimiter = cache.get(key, new Callable<RateLimiter>() {
                    @Override
                    public RateLimiter call() throws Exception {
                        return RateLimiter.create(DEFAULT_LIMIT);
                    }
                });
            } catch (ExecutionException e) {
                log.error("get rate limiter error",e);
            }
            if(!rateLimiter.tryAcquire()) { //未请求到limiter则返回超额提示

                response.getWriter().write("");
                return;
            }

        }

        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {

    }

}
