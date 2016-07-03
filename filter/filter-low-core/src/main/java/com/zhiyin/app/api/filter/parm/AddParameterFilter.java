package com.zhiyin.app.api.filter.parm;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

/**
 * Servlet Filter implementation class CommonFilter
 */
public class AddParameterFilter implements Filter {

	private static Field requestField;

	private static Field parametersParsedField;

	private static Field coyoteRequestField;

	private static Field parametersField;

	private static Field hashTabArrField;

	public AddParameterFilter() {

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
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


	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		AddParameterRequestWrapper requestWrapper = new AddParameterRequestWrapper(
				(HttpServletRequest) request);

		Map<String , String[]> map = getRequestMap(request);
        if(map != null) {
            map.put("fuck" , new String[] {"fuck you!"});
        }
        map.put("sss", new String[] {"fuck you!"});
		for (Map.Entry<String, String[]> entry : requestWrapper
				.getParameterMap().entrySet()) {

			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue()[0]);

		}

		chain.doFilter(requestWrapper, response);

	}
	
	@SuppressWarnings("unchecked")
    private Map<String , String[]> getRequestMap(ServletRequest request) {
        try {
            Object innerRequest = requestField.get(request);
            parametersParsedField.setBoolean(innerRequest, true);
            Object coyoteRequestObject = coyoteRequestField.get(innerRequest);
            Object parameterObject = parametersField.get(coyoteRequestObject);
            return (Map<String,String[]>)hashTabArrField.get(parameterObject);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
