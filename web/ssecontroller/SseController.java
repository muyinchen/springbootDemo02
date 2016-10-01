package com.zhiqiu.web.ssecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Author  知秋
 * Created by kauw on 2016/10/2.
 */
@Controller
public class SseController {
    //输出的媒体类型为text/event-stream.是服务器端SSE的支持，需要新浏览器支持
    @RequestMapping(value = "/push",produces = "text/event-stream")
    @ResponseBody
    public String push(){
        Random random=new Random();
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3 "+random.nextInt()+"\n";
    }
}
