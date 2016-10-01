package com.zhiqiu.web.hcontroller;

import com.zhiqiu.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@RestController //声明是控制器，其实源码可以看出是@Controller与 @ResponseBody的组合注解
@RequestMapping("/rest")
public class DemoRestController {
    //返回对象会自动转换为json
    @RequestMapping(value = "/getjson",produces = "application/json;charset=UTF-8")
    public DemoObj getjson(DemoObj demoObj){
        return new DemoObj(demoObj.getId()+1,demoObj.getName()+"xx");
    }
    //返回对象会自动转换为xml
    @RequestMapping(value ="/getxml",produces = "application/xml;charset=UTF-8")
    public DemoObj getxml(DemoObj obj){
        return new DemoObj(obj.getId()+1,obj.getName()+"xx");
    }
}
