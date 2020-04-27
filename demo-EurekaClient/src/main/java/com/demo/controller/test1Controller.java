package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@Controller
public class test1Controller {

    @Value("${test.user.name}")
    private String name;

    @Value("${test.user.age}")
    private String age;

    @Value("${test.user.sex}")
    private String sex;


    @RequestMapping("/test")
    @ResponseBody
    public String getInfo(){
        return "name:"+name+"age:"+age;
    }
    //master test
}
