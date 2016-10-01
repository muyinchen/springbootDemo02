package com.zhiqiu.web.adController;

import com.zhiqiu.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@Controller
public class adviceController {
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg")String msg, DemoObj obj){
        throw new IllegalArgumentException("就是各种对不起！这是来自@ModelAttribute的消息： "+msg);
    }
}
