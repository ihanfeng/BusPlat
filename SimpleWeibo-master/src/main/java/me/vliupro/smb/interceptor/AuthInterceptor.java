package me.vliupro.smb.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

import java.util.Map;

/**
 * Created by vliupro on 16-5-30.
 */
public class AuthInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public void destroy() {
        logger.debug("结束登陆验证拦截器");
    }

    @Override
    public void init() {
        logger.debug("进入登陆验证拦截器");
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map session = invocation.getInvocationContext().getSession();
        if (session.get("user") != null) {
            return invocation.invoke();
        } else {
            logger.debug("未登录，请登陆之后再进行操作");
            return "noLogin";
        }
    }
}
