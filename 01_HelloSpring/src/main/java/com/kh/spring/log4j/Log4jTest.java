package com.kh.spring.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerFactory;

/*import org.apache.log4j.Level;
import org.apache.log4j.Logger;*/

public class Log4jTest {
	//org.apache.log4j.Logger객체생성
	/*private Logger logger = Logger.getLogger(Log4jTest.class);*/
	private Logger logger = LoggerFactory.getLogger(Log4jTest.class);
	public static void main(String[] args) {
/*		new Log4jTest().test();
*/	
	//org.slf4j.Logger 객체 생성
	// Simple Logging Facade 4 JAva
		new Log4jTest().test2();
		
	}
/*	private void test() {
		// TODO Auto-generated method stub
		//TRACE < DEBUG < INFO < WARN < ERROR < FATAL
		logger.setLevel(Level.ERROR);
		logger.fatal("Fatal 로그");
		logger.error("error 로그");
		logger.warn("warn 로그");
		logger.info("info 로그");
		logger.debug("debug 로그");
		logger.trace("trace 로그");
		
	}
*/
	
	private void test2() {
		// TODO Auto-generated method stub
		//TRACE < DEBUG < INFO < WARN < ERROR < FATAL
		logger.error("error 로그");
		logger.warn("warn 로그");
		logger.info("info 로그");
		logger.debug("debug 로그");
		logger.trace("trace 로그");
		
	}
	
	
}
