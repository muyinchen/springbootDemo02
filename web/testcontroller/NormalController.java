package com.zhiqiu.web.testcontroller;

import com.zhiqiu.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author  知秋
 * Created by kauw on 2016/10/2.
 */
@Controller
public class NormalController {
    @Autowired
    DemoService demoService;
    @RequestMapping("/normal")
    public String testPage(Model model){
        model.addAttribute("msg",demoService.saySomething());
        return "page";
    }
}
