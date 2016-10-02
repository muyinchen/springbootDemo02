package com.zhiqiu.web.testcontroller;

import com.zhiqiu.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author  知秋
 * Created by kauw on 2016/10/2.
 */
@RestController
public class MyRestController {
    @Autowired
    DemoService demoService;
    @RequestMapping(value ="/testRest",produces = "text/plain;charset=UTF-8")
    public String testRest(){
        return demoService.saySomething();
    }
}
