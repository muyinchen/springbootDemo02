package com.zhiqiu.web.hcontroller;

import com.zhiqiu.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Author  知秋
 * Created by kauw on 2016/10/1.
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {
    @RequestMapping(produces = "text/plain;charset=UTF-8")
    public @ResponseBody String index(HttpServletRequest request){
        return "url: "+request.getRequestURL()+" can access";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String demoPathVar(@PathVariable String str,
                                            HttpServletRequest request){
        return "url: "+request.getRequestURL()+" can access,str: "+str;
    }
    //request参数获取，访问路径为/anno/requestParam?id=1
    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=UTF-8")
    public @ResponseBody String passRequestParam(Long id,
                                                 HttpServletRequest request)
    {
        return "url: "+request.getRequestURL()+" can access,id: "+id;
    }
    //解释参数到对象，访问路径为/anno/obj?id=1&name=xxx
    @RequestMapping(value = "/obj" , produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String passObj(DemoObj obj, HttpServletRequest request){
        return "url: "+request.getRequestURL()+" can access,obj id:"+
                obj.getId()+" obj name: "+obj.getName();
    }
    //演示不同的路径到相同的方法，访问的路径为/anno/name1或者/anno/name2
    @RequestMapping(value = {"/name1","/name2"},produces ="text/plain;charset=UTF-8" )
    public @ResponseBody String remove(HttpServletRequest request){
        return "url： "+request.getRequestURL()+" can access ";
    }
}
