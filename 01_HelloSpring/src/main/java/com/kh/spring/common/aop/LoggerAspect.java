package com.kh.spring.common.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.vo.Member;



/**
 * 1. 선언적 AOP :  일반클래스를 설정파일에서 등록함.
 * 2. 프로그래밍적 AOP : @ annotation을 사용해서 ASPECT 등록함.
 * @author aquar
 *
 */

@Component
@Aspect
public class LoggerAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Pointcut("execution(* com.kh.aop.memo..*(..))")
	public void myPointcut() {}
	//advice 메소드
	@Around("myPointcut()")*/
	@AfterReturning(pointcut="execution(* com.kh.spring.member.controller.MemberController.memberLogin(..))", returning = "obj")
	public void afterReturnings(JoinPoint joinPoint, Object obj ){
		ModelAndView m = (ModelAndView) obj;
		logger.info(((Member) m.getModel().get("memberLoggedIn")).getUserId());
		
	}
	
	
	
	@AfterReturning(pointcut="execution(* com.kh.spring.member.controller.MemberController.memberLogin(..))", returning = "mav")
	public void afterReturning(JoinPoint joinPoint, ModelAndView mav){

		
		Member m = (Member) mav.getModelMap().get("memberLoggedIn");
		
		logger.info(m.getUserId());
	}
}
