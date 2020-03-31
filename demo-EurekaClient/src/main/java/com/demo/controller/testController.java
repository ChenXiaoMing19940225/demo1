package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @Autowired
    ApplicationContext context;

    /*
      读取配置文件
     */
    @GetMapping("/getprop")
    public String getName(){
        return context.getEnvironment().getProperty("test.user.name");
    }
}
