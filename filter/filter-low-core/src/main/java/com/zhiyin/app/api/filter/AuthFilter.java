package com.zhiyin.app.api.filter;

import javax.servlet.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AuthFilter implements Filter {

    private static Field requestField;

    private static Field parametersParsedField;

    private static Field coyoteRequestField;

    private static Field parametersField;

    private static Field hashTabArrField;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            Class clazz = Class
                    .forName("org.apache.catalina.connector.RequestFacade");
            requestField = clazz.getDeclaredField("request");
            requestField.setAccessible(true);

            parametersParsedField = requestField.getType().getDeclaredField(
                    "parametersParsed");
            parametersParsedField.setAccessible(true);

            coyoteRequestField = requestField.getType().getDeclaredField(
                    "coyoteRequest");
            coyoteRequestField.setAccessible(true);

            parametersField = coyoteRequestField.getType().getDeclaredField(
                    "parameters");
            parametersField.setAccessible(true);

            hashTabArrField = parametersField.getType().getDeclaredField(
                    "paramHashStringArray");
            hashTabArrField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace(); // To change body of catch statement use File |
            // Settings | File Templates.
        }

    }

    @SuppressWarnings("unchecked")
    private Map<String, String[]> getRequestMap(ServletRequest request) {
        try {
            Object innerRequest = requestField.get(request);
            parametersParsedField.setBoolean(innerRequest, true);
            Object coyoteRequestObject = coyoteRequestField.get(innerRequest);
            Object parameterObject = parametersField.get(coyoteRequestObject);
            return (Map<String, String[]>) hashTabArrField.get(parameterObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        System.out.println("request" + request.getParameter("access_token"));

        // Map<String , String[]> map = getRequestMap(request);
        // if(map != null) {
        // map.put("fuck" , new String[] {"fuck you!"});
        // }

        for (Map.Entry<String, String[]> entry : request.getParameterMap()
                .entrySet()) {

            System.out.println("Key = " + entry.getKey() + ", Value = "
                    + entry.getValue()[0]);

        }

        getRequestData(request);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    public Map<String, Object> getRequestData(ServletRequest request) {

        // request.getp
        Map<String, Object> map = null;
        Enumeration enumeration = request.getAttributeNames();// 获取表单内所有元素
        if (enumeration.hasMoreElements()) {
            map = new HashMap<String, Object>();
            while (enumeration.hasMoreElements()) {
                String inputName = (String) enumeration.nextElement();// 获取元素名
                map.put(inputName, request.getParameter(inputName));// 以表单名作为key
                System.out.println(inputName + ";" + map.get(inputName));
            }
        }
        return map;
    }
}
