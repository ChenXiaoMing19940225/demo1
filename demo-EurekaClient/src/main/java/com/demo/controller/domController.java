package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.controller.Util.exeOperation;
import com.demo.controller.Util.test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

@Controller
@RequestMapping("/api")
public class domController {

    private static String path;

    @Value("${exeInfo.config.windows.filePath}")
    public  void setPath(String path) {
        domController.path = path;
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    @ResponseBody
    public String getData(@RequestBody JSONObject object){
        System.out.println("test");
        test.addOrRemove(test.insert(path),object);
        return path;
    }
    @RequestMapping(value = "/addElement",method = RequestMethod.GET)
    @ResponseBody
    public String addXmlElement(){
         exeOperation.finishExe();
         test.add(test.insert(path));
         exeOperation.restartExe();
         return "success";
    }

    @RequestMapping(value = "/getProperty",method = RequestMethod.GET)
    @ResponseBody
    public String getProperty(){
        System.out.println(path);
        return path;
    }
}
