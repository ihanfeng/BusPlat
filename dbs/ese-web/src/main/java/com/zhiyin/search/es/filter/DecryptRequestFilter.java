//package com.zhiyin.search.es.filter;
//
////(C) 1998-2015 Information Desire Software GmbH
////www.infodesire.com
//
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 解密过滤器
// *
// * @author eugen
// *
// */
//public class DecryptRequestFilter implements Filter {
//
//	private static final Logger log = LoggerFactory
//			.getLogger(DecryptRequestFilter.class.getName());
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
//	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response,
//			FilterChain chain) throws IOException, ServletException {
//
//		System.out.println("ss");
//		if (request instanceof HttpServletRequest) {
//
//
//			   String uri = ((HttpServletRequest)request).getRequestURI();
//
//
//				DecryptRequestDataWrapper myRequestWrapper = null;
//				try {
//					myRequestWrapper = new DecryptRequestDataWrapper(
//							(HttpServletRequest) request);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			   chain.doFilter(myRequestWrapper, response);
//
//			   // 针对测试的url进行解密
////			   if(uri.equals("/appapi/test/testEncrypt")){
////				   System.out.println("");
////
////					DecryptRequestDataWrapper myRequestWrapper = null;
////					try {
////						myRequestWrapper = new DecryptRequestDataWrapper(
////								(HttpServletRequest) request);
////					} catch (Exception e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				   chain.doFilter(myRequestWrapper, response);
////			   }else{
////				   chain.doFilter(request, response);
////			  }
//
//
//
//		} else {
//
//			// non http request, can not create wrapper
//
//		}
//
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see javax.servlet.Filter#destroy()
//	 */
//	@Override
//	public void destroy() {
//		// nothing to do
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
//	 */
//	@Override
//	public void init(FilterConfig arg0) throws ServletException {
//		// nothing to do
//	}
//
//}