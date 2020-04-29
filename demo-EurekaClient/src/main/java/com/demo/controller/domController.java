package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.controller.Util.test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

@Controller
@RequestMapping("/api")
public class domController {

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String getData(@RequestBody JSONObject object){
        System.out.println("test");
        test.addOrRemove(test.insert("F:\\powerRouter\\routerConfig.xml"),object);
        return "hello";
    }
}
