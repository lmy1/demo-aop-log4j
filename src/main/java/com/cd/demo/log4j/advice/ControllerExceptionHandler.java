package com.cd.demo.log4j.advice;

import com.cd.demo.log4j.pojo.Response;
import com.cd.demo.log4j.pojo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerExceptionHandler {
	private Logger log = LoggerFactory.getLogger(getClass());

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Response handleException(Exception e) {
		if (e.getCause() != null) {
			log.error(e.getCause().getMessage());
			e.printStackTrace();
			return new Response(1, ResultCode.FAILED_SYSTEM.appendMsg(e.getCause().getMessage()), null);
		} else {
			log.error(e.getMessage());
			e.printStackTrace();
			return new Response(1, ResultCode.FAILED_SYSTEM.appendMsg(e.getMessage()), null);
		}
	}

}