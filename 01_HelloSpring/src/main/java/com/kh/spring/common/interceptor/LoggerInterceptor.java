package com.kh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.kh.spring.member.model.vo.Member;
/**
 * 1. preHandle : DispatcherServlet => controller
 * 2. postHandle : controller => DispatcherServlet 
 * 3. afterCompletion : view단 처리가 끝난 후. 응답객체가 완성된 후 
 * 
 * @author aquar
 *
 */

public class LoggerInterceptor extends HandlerInterceptorAdapter{
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		if(logger.isDebugEnabled()) {
			logger.debug("============= [PreHandle START] ============= ");
			logger.debug(request.getRequestURI());
			logger.debug("============= [PreHandle END] ============= ");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("============= [postHandle START] ============= ");
			logger.debug(request.getRequestURI());
			logger.debug("============= [postHandle END] ============= ");
		}
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(logger.isDebugEnabled()) {
			//logger.debug(request.getRequestURI());
			HttpSession session = request.getSession();
			Member m = (Member)session.getAttribute("memberLoggedIn");
			MDC.put("USERID", m.getUserId());
			logger.debug(m.getUserId());
			//logger.debug("============= [afterComletion END] ============= ");
		}
		super.afterCompletion(request, response, handler, ex);
	}
	
	

}
