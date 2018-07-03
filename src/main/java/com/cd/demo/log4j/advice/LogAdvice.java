package com.cd.demo.log4j.advice;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class LogAdvice {
	private final static Logger logger = LoggerFactory.getLogger(LogAdvice.class);
	
	@Pointcut(value = "execution(public * com.cd.demo.log4j.controller.*.*(..))")
	public void log() {
		
	}
	
	@Before("log()")
	public void printLog(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        logger.debug("request userId={}",1);
        
        //url  
        logger.debug("request url= {} {}", request.getMethod(), request.getRequestURI());
  
        //ip  
        logger.debug("request ip={}", request.getRemoteAddr());

        logger.debug("request parameters={}", JSON.toJSONString(request.getParameterMap()));

        //param
    	String jsonString = JSON.toJSONString(joinPoint.getArgs());
        logger.debug("request body={}", jsonString);
	}
	
//    @After("log()")
//    public void doAfter(){
//    	logger.info("执行了doAfter");
//    }

    @AfterReturning(returning="obj", pointcut = "log()")
    public void doAfterReturnig(Object obj){
    	String jsonString = JSON.toJSONString(obj);
        logger.info("response={}", jsonString);
    }
}
