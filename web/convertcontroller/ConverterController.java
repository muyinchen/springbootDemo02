package com.zhiqiu.web.convertcontroller;

import com.zhiqiu.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@Controller
public class ConverterController {

    @RequestMapping(value = "/convert",produces = {"application/g-zhiqiu"})
    @ResponseBody
        public DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }
}
