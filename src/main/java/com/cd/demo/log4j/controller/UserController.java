package com.cd.demo.log4j.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by li.mingyang on 2018/5/28.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping
    public String getUser(){

        if(logger.isInfoEnabled()) {
            logger.info("{}普通日志", "info");
        }
        if(logger.isDebugEnabled()) {
            logger.debug("{}开发日志", "debug");
        }
        if (logger.isErrorEnabled()){
            logger.error("{}错误日志", "error");
        }


        return "hello world";
    }
}
